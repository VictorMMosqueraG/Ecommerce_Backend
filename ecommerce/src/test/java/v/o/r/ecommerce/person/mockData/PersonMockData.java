package v.o.r.ecommerce.person.mockData;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import v.o.r.ecommerce.persons.dto.CreatePersonDto;
import v.o.r.ecommerce.persons.entities.PersonEntity;
import v.o.r.ecommerce.user.mockData.userMockData;

public class PersonMockData {
    

    //NOTE: methods for save
    public static CreatePersonDto createPersonDto(){
        CreatePersonDto dto = new CreatePersonDto();

        dto.firstName="firstName";
        dto.lastName="lastName";
        dto.address="address";
        dto.city="city";
        dto.department="department";
        dto.methodOfPay="Cash";
        dto.phoneNumber="3207775152";
        dto.user=1L;

        return dto;
    } 

    public static PersonEntity createPersonEntity(
        CreatePersonDto createPersonDto
    ){
        PersonEntity entity = new PersonEntity();

        entity.setAddress(createPersonDto.address);
        entity.setCity(createPersonDto.city);
        entity.setDepartment(createPersonDto.department);
        entity.setFirstName(createPersonDto.firstName);
        entity.setLastName(createPersonDto.lastName);
        entity.setMethodOfPay(createPersonDto.methodOfPay);
        entity.setPhoneNumber(createPersonDto.phoneNumber);
        entity.setUser(userMockData.userEntity());

        return entity;
    }

    //NOTE: Methods for find
    public static List<PersonEntity> personList(){
        PersonEntity entity1 = new PersonEntity();
        entity1.setId(1L);
        entity1.setAddress("address1");
        entity1.setCity("city1");
        entity1.setDepartment("department1");
        entity1.setFirstName("firstName1");
        entity1.setLastName("lastName1");
        entity1.setMethodOfPay("methodOfPay1");
        entity1.setPhoneNumber("phoneNumber1");
        entity1.setUser(userMockData.userEntity());
        
        PersonEntity entity2 = new PersonEntity();
        entity2.setId(2L);
        entity2.setAddress("address2");
        entity2.setCity("city2");
        entity2.setDepartment("department2");
        entity2.setFirstName("firstName2");
        entity2.setLastName("lastName2");
        entity2.setMethodOfPay("methodOfPay2");
        entity2.setPhoneNumber("phoneNumber2");
        entity2.setUser(userMockData.userEntity());


        return Arrays.asList(entity1,entity2);
    }

    public static List<PersonEntity> personListFLatten(){
        PersonEntity entity1 = new PersonEntity();
        entity1.setId(1L);
        entity1.setFirstName("firstName1");
        entity1.setLastName("lastName1");
        
        PersonEntity entity2 = new PersonEntity();
        entity2.setId(2L);
        entity2.setFirstName("firstName2");
        entity2.setLastName("lastName2");

        return Arrays.asList(entity1,entity2);   
    }

    //NOTE: method for findDetail
    public static Optional<PersonEntity> findPersonDetail(){
        PersonEntity entity1 = new PersonEntity();
        entity1.setId(1L);
        entity1.setAddress("address1");
        entity1.setCity("city1");
        entity1.setDepartment("department1");
        entity1.setFirstName("firstName1");
        entity1.setLastName("lastName1");
        entity1.setMethodOfPay("methodOfPay1");
        entity1.setPhoneNumber("phoneNumber1");
        entity1.setUser(userMockData.userEntity());
        
        return Optional.of(entity1);
    }
}
