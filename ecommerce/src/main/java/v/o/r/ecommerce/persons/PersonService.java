package v.o.r.ecommerce.persons;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import v.o.r.ecommerce.common.interfaces.persons.IPersonService;
import v.o.r.ecommerce.persons.dto.CreatePersonDto;
import v.o.r.ecommerce.persons.dto.PaginationPersonDto;
import v.o.r.ecommerce.persons.entities.PersonEntity;
import v.o.r.ecommerce.persons.repositories.PersonRepository;
import v.o.r.ecommerce.users.UserService;

@Service
public class PersonService implements IPersonService{
    
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UserService userService;

    public PersonEntity save(CreatePersonDto createPerson){
        PersonEntity person = new PersonEntity();

        person.setFirstName(createPerson.firstName);
        person.setLastName(createPerson.lastName);
        person.setMethodOfPay(createPerson.methodOfPay);
        person.setPhoneNumber(createPerson.phoneNumber);
        person.setAddress(createPerson.address);
        person.setCity(createPerson.city);
        person.setDepartment(createPerson.department);

        //find user
        person.setUser(userService.findByIdOrFail(createPerson.user).get());

        return personRepository.save(person);
    }

    public List<Map<String, Object>> find(PaginationPersonDto paginationPerson){
        List<PersonEntity> foundPerson= personRepository.findAll();

        boolean flatten = paginationPerson!=null && paginationPerson.getFlatten();
        int limit = paginationPerson!=null && paginationPerson.getLimit() !=0 ? paginationPerson.getLimit():50;
        int offset = paginationPerson!=null && paginationPerson.getOffset() != 0 ? paginationPerson.getOffset() : 0;
        String sortOrder = paginationPerson!=null ? paginationPerson.getSortOrder() : null;
        String firstName = paginationPerson!=null ? paginationPerson.getFirstName():null;
        String lastName = paginationPerson!=null ? paginationPerson.getLastName():null;

        if(flatten){
            if(flatten==true && ( limit !=50  || offset != 0 || firstName != null || lastName != null || sortOrder!=null)){
                throw new IllegalArgumentException("The PaginationPersonDto object cannot have other fields besides 'flatten'.");
            }

            Map<String,Object> response = new LinkedHashMap<>();
            response.put("context","person");
            response.put("total", foundPerson.size());
            response.put("data", foundPerson.stream()
                .map(person ->{
                    Map<String,Object> personMap = new LinkedHashMap<>();
                    personMap.put("id", person.getId());
                    String name = person.getFirstName() + " " + person.getLastName();
                    personMap.put("name",name);
                    return personMap;
                })
                .collect(Collectors.toList()));
            return Collections.singletonList(response);
        }
       
        //logic for sortOrder DESC, this is default ASC
        Comparator<Map<String, Object>> sort = Comparator.comparing(m -> (String) m.get("firstName"));

        if (sortOrder != null && sortOrder.equalsIgnoreCase("desc")) {
            sort = sort.reversed();
        }

        //filters bases on firstName and lastName
        Predicate<PersonEntity> firstNameFilter = person -> firstName == null || person.getFirstName().toLowerCase().contains(firstName.toLowerCase());
        Predicate<PersonEntity> lastNameFilter = person -> lastName == null || person.getLastName().toLowerCase().contains(lastName.toLowerCase());

        //iterates from foundPerson and extract all data
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("context", "person");
        response.put("total", foundPerson.size());
        response.put("data", foundPerson.stream()
                .filter(firstNameFilter.and(lastNameFilter))
                .map(person -> {
                    Map<String, Object> personMap = new LinkedHashMap<>();
                    personMap.put("id", person.getId());
                    personMap.put("firstName", person.getFirstName());
                    personMap.put("lastName", person.getLastName());
                    personMap.put("methodOfPay", person.getMethodOfPay());
                    personMap.put("phoneNumber", person.getPhoneNumber());
                    personMap.put("address", person.getAddress());
                    personMap.put("city", person.getCity());
                    personMap.put("department", person.getDepartment());
                    // Exclude the "password" field from the "user" object
                    Map<String, Object> userMap = new LinkedHashMap<>();
                    userMap.put("id", person.getUser().getId());
                    userMap.put("email", person.getUser().getEmail());
                    userMap.put("role", person.getUser().getRole());//COMEBACK: this is necessary?
                    personMap.put("user", userMap);
                    return personMap;
                })
                .sorted(sort)
                .skip(offset)
                .limit(limit)
                .collect(Collectors.toList()));

        List<Map<String, Object>> resultList = new ArrayList<>();
        resultList.add(response);
        return resultList;        
    }

    public Optional<PersonEntity> findDetail(Long id){
        return this.findByIdOrFail(id);
    }


    //NOTE: methods bases
    Optional<PersonEntity> findById(Long id){
        Optional<PersonEntity> foundPerson = this.personRepository.findById(id);
        return foundPerson;
    }

    Optional<PersonEntity> findByIdOrFail(Long id){
        Optional<PersonEntity> foundPerson = id!=null ?
            this.findById(id):null;

        if(foundPerson==null || foundPerson.isEmpty()){
            throw new NoSuchElementException("Person with id " + id + " not found.");
        }    

        return foundPerson;
    }
}
