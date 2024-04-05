package v.o.r.ecommerce.persons;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import v.o.r.ecommerce.common.interfaces.persons.IPersonService;
import v.o.r.ecommerce.persons.dto.CreatePerson;
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
}
