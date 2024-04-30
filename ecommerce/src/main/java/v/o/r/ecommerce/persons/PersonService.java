package v.o.r.ecommerce.persons;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import v.o.r.ecommerce.common.interfaces.persons.IPersonService;
import v.o.r.ecommerce.persons.dto.CreatePerson;
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

    public PersonEntity save(CreatePerson createPerson){
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

    public List<Map<String, Object>> find(PaginationPersonDto paginationPersonDto) {
        List<PersonEntity> listPerson = personRepository.findAll();

        boolean flatten = paginationPersonDto != null && paginationPersonDto.isFlatten();
        int limit = paginationPersonDto != null && paginationPersonDto.getLimit() != 0 ? paginationPersonDto.getLimit(): 50;
        int offset = paginationPersonDto != null && paginationPersonDto.getOffset() != 0 ? paginationPersonDto.getLimit(): 0;
        String sortOrder = paginationPersonDto != null ? paginationPersonDto.getSortOrder() : null;
        String firstName = paginationPersonDto != null ? paginationPersonDto.getFirstName() : null;
        String lastName = paginationPersonDto != null ? paginationPersonDto.getLastName(): null;

        if (flatten == true) {
            if (limit != 50 || offset != 0 || firstName != null || lastName != null) {
                throw new IllegalArgumentException(
                        "The PaginationUserDto object cannot have other fields besides 'flatten'.");
            }

            Map<String, Object> response = new LinkedHashMap<>();
            response.put("context", "persons");
            response.put("total", listPerson.size());
            response.put("date", listPerson.stream()
                    .map(person -> {
                        Map<String, Object> personMap = new LinkedHashMap<>();
                        personMap.put("id", person.getId());
                        personMap.put("firstName", person.getFirstName());
                        personMap.put("lastName", person.getLastName());
                        personMap.put("Address", person.getAddress());
                        personMap.put("city", person.getCity());
                        personMap.put("department", person.getDepartment());
                        personMap.put("method of pay", person.getMethodOfPay());
                        personMap.put("phoneNumber", person.getPhoneNumber());
                        personMap.put("user", person.getUser().getEmail());
                        return personMap;
                    })
                    .collect(Collectors.toList()));

            return Collections.singletonList(response);
        }

        Comparator<Map<String, Object>> sort = Comparator.comparing(m -> (String) m.get("name"));

        if (sortOrder != null && sortOrder.equalsIgnoreCase("desc")) {
            sort = sort.reversed();
        }

        Predicate<PersonEntity> firstNameFind = person -> firstName == null || person.getFirstName().toLowerCase().contains(firstName.toLowerCase());
        Predicate<PersonEntity> lastNameFind = person -> lastName == null || person.getLastName().toLowerCase().contains(lastName.toLowerCase());

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("context", "products");
        response.put("total", listPerson.size());
        response.put("date", listPerson.stream()
                .filter(firstNameFind.and(lastNameFind))
                .map(person -> {
                    Map<String, Object> personMap = new LinkedHashMap<>();
                    personMap.put("id", person.getId());
                    personMap.put("firstName", person.getFirstName());
                    personMap.put("lastName", person.getLastName());
                    personMap.put("Address", person.getAddress());
                    personMap.put("city", person.getCity());
                    personMap.put("department", person.getDepartment());
                    personMap.put("method of pay", person.getMethodOfPay());
                    personMap.put("phoneNumber", person.getPhoneNumber());
                    personMap.put("user", person.getUser().getEmail());
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
}
