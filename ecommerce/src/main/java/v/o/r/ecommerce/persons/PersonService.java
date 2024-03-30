package v.o.r.ecommerce.persons;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import v.o.r.ecommerce.common.interfaces.persons.IPersonService;
import v.o.r.ecommerce.persons.dto.CreatePerson;
import v.o.r.ecommerce.persons.entities.PersonEntity;
import v.o.r.ecommerce.persons.repositories.PersonRepository;
import v.o.r.ecommerce.users.UserService;
import v.o.r.ecommerce.users.entities.UserEntity;

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
        Optional<UserEntity> foundUser = userService.findById(createPerson.user);
        person.setUser(foundUser.get());

        return personRepository.save(person);
    }
}
