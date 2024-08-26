package v.o.r.ecommerce.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import v.o.r.ecommerce.person.mockData.PersonMockData;
import v.o.r.ecommerce.persons.PersonService;
import v.o.r.ecommerce.persons.dto.CreatePersonDto;
import v.o.r.ecommerce.persons.dto.PaginationPersonDto;
import v.o.r.ecommerce.persons.entities.PersonEntity;
import v.o.r.ecommerce.persons.repositories.PersonRepository;
import v.o.r.ecommerce.user.mockData.userMockData;
import v.o.r.ecommerce.users.UserService;

public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private PersonService personService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // NOTE: Test save

    @Test
    public void testSaveSuccessfully() {
        // Initialize variable
        CreatePersonDto createPersonDto = PersonMockData.createPersonDto();
        PersonEntity personEntity = PersonMockData.createPersonEntity(createPersonDto);

        // Configure method when called
        when(personRepository.save(
                any(PersonEntity.class))).thenReturn(personEntity);

        when(userService.findByIdOrFail(
                createPersonDto.user)).thenReturn(Optional.of(userMockData.userEntity()));

        // Call method service
        PersonEntity result = personService.save(createPersonDto);

        // Asserts
        assertEquals(personEntity, result);

        // Verify
        verify(personRepository).save(any(PersonEntity.class));
    }

    @Test
    public void testSaveWithoutMethodOfPay() {
        // Initialize variable
        CreatePersonDto createPersonDto = PersonMockData.createPersonDto();
        createPersonDto.methodOfPay = null;

        PersonEntity personEntity = PersonMockData.createPersonEntity(createPersonDto);

        // Configure method when called
        when(personRepository.save(
                any(PersonEntity.class))).thenReturn(personEntity);

        when(userService.findByIdOrFail(
                createPersonDto.user)).thenReturn(Optional.of(userMockData.userEntity()));

        // Call method service
        PersonEntity result = personService.save(createPersonDto);

        // Asserts
        assertEquals(personEntity, result);

        // Verify
        verify(personRepository).save(any(PersonEntity.class));
    }

    @Test
    public void testSaveWithoutPhoneNumber() {
        // Initialize variable
        CreatePersonDto createPersonDto = PersonMockData.createPersonDto();
        createPersonDto.phoneNumber = null;

        PersonEntity personEntity = PersonMockData.createPersonEntity(createPersonDto);

        // Configure method when called
        when(personRepository.save(
                any(PersonEntity.class))).thenReturn(personEntity);

        when(userService.findByIdOrFail(
                createPersonDto.user)).thenReturn(Optional.of(userMockData.userEntity()));

        // Call method service
        PersonEntity result = personService.save(createPersonDto);

        // Asserts
        assertEquals(personEntity, result);

        // Verify
        verify(personRepository).save(any(PersonEntity.class));
    }

    // NOTE: Test find
    @Test
    public void testFindSuccessfully() {
        // Initialize variable
        List<PersonEntity> entities = PersonMockData.personList();
        PaginationPersonDto paginationPersonDto = new PaginationPersonDto();

        // Configure method when called
        when(personRepository.findAll()).thenReturn(entities);

        // Call method service
        List<Map<String, Object>> result = personService.find(paginationPersonDto);

        Map<String, Object> response = result.get(0);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        // Asserts
        assertEquals("person", response.get("context"));
        assertEquals(2, response.get("total"));
        // Data 1
        assertEquals(1L, data.get(0).get("id"));
        assertEquals("address1", data.get(0).get("address"));
        assertEquals("city1", data.get(0).get("city"));
        assertEquals("department1", data.get(0).get("department"));
        assertEquals("firstName1", data.get(0).get("firstName"));
        assertEquals("lastName1", data.get(0).get("lastName"));
        assertEquals("methodOfPay1", data.get(0).get("methodOfPay"));
        assertEquals("phoneNumber1", data.get(0).get("phoneNumber"));
        // Data 2
        assertEquals(2L, data.get(1).get("id"));
        assertEquals("address2", data.get(1).get("address"));
        assertEquals("city2", data.get(1).get("city"));
        assertEquals("department2", data.get(1).get("department"));
        assertEquals("firstName2", data.get(1).get("firstName"));
        assertEquals("lastName2", data.get(1).get("lastName"));
        assertEquals("methodOfPay2", data.get(1).get("methodOfPay"));
        assertEquals("phoneNumber2", data.get(1).get("phoneNumber"));
    }

    @Test
    public void testWithLimit() {
        // Initialize variable
        List<PersonEntity> entities = PersonMockData.personList();
        PaginationPersonDto paginationPersonDto = new PaginationPersonDto();
        paginationPersonDto.setLimit(1);

        // Configure method when called
        when(personRepository.findAll()).thenReturn(entities);

        // Call method service
        List<Map<String, Object>> result = personService.find(paginationPersonDto);

        Map<String, Object> response = result.get(0);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        // Asserts
        assertEquals("person", response.get("context"));
        assertEquals(2, response.get("total"));
        // Data 1
        assertEquals(1L, data.get(0).get("id"));
        assertEquals("address1", data.get(0).get("address"));
        assertEquals("city1", data.get(0).get("city"));
        assertEquals("department1", data.get(0).get("department"));
        assertEquals("firstName1", data.get(0).get("firstName"));
        assertEquals("lastName1", data.get(0).get("lastName"));
        assertEquals("methodOfPay1", data.get(0).get("methodOfPay"));
        assertEquals("phoneNumber1", data.get(0).get("phoneNumber"));
    }

    @Test
    public void testWithOffset() {
        // Initialize variable
        List<PersonEntity> entities = PersonMockData.personList();
        PaginationPersonDto paginationPersonDto = new PaginationPersonDto();
        paginationPersonDto.setOffset(1);

        // Configure method when called
        when(personRepository.findAll()).thenReturn(entities);

        // Call method service
        List<Map<String, Object>> result = personService.find(paginationPersonDto);

        Map<String, Object> response = result.get(0);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        // Asserts
        assertEquals("person", response.get("context"));
        assertEquals(2, response.get("total"));
        // Data 1
        assertEquals(2L, data.get(0).get("id"));
        assertEquals("address2", data.get(0).get("address"));
        assertEquals("city2", data.get(0).get("city"));
        assertEquals("department2", data.get(0).get("department"));
        assertEquals("firstName2", data.get(0).get("firstName"));
        assertEquals("lastName2", data.get(0).get("lastName"));
        assertEquals("methodOfPay2", data.get(0).get("methodOfPay"));
        assertEquals("phoneNumber2", data.get(0).get("phoneNumber"));
    }

    @Test
    public void testWithSortOrder() {
        // Initialize variable
        List<PersonEntity> entities = PersonMockData.personList();
        PaginationPersonDto paginationPersonDto = new PaginationPersonDto();
        paginationPersonDto.setSortOrder("DESC");// COMEBACK: changed string at Enum ASC or DESC

        // Configure method when called
        when(personRepository.findAll()).thenReturn(entities);

        // Call method service
        List<Map<String, Object>> result = personService.find(paginationPersonDto);

        Map<String, Object> response = result.get(0);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        // Asserts
        assertEquals("person", response.get("context"));
        assertEquals(2, response.get("total"));
        // Data 1
        assertEquals(2L, data.get(0).get("id"));
        assertEquals("address2", data.get(0).get("address"));
        assertEquals("city2", data.get(0).get("city"));
        assertEquals("department2", data.get(0).get("department"));
        assertEquals("firstName2", data.get(0).get("firstName"));
        assertEquals("lastName2", data.get(0).get("lastName"));
        assertEquals("methodOfPay2", data.get(0).get("methodOfPay"));
        assertEquals("phoneNumber2", data.get(0).get("phoneNumber"));
        // Data 2
        assertEquals(1L, data.get(1).get("id"));
        assertEquals("address1", data.get(1).get("address"));
        assertEquals("city1", data.get(1).get("city"));
        assertEquals("department1", data.get(1).get("department"));
        assertEquals("firstName1", data.get(1).get("firstName"));
        assertEquals("lastName1", data.get(1).get("lastName"));
        assertEquals("methodOfPay1", data.get(1).get("methodOfPay"));
        assertEquals("phoneNumber1", data.get(1).get("phoneNumber"));
    }

    @Test
    public void testWithFirstName() {
        // Initialize variable
        List<PersonEntity> entities = PersonMockData.personList();
        PaginationPersonDto paginationPersonDto = new PaginationPersonDto();
        paginationPersonDto.setFirstName("firstName1");

        // Configure method when called
        when(personRepository.findAll()).thenReturn(entities);

        // Call method service
        List<Map<String, Object>> result = personService.find(paginationPersonDto);

        Map<String, Object> response = result.get(0);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        // Asserts
        assertEquals("person", response.get("context"));
        assertEquals(2, response.get("total"));
        // Data 1
        assertEquals(1L, data.get(0).get("id"));
        assertEquals("address1", data.get(0).get("address"));
        assertEquals("city1", data.get(0).get("city"));
        assertEquals("department1", data.get(0).get("department"));
        assertEquals("firstName1", data.get(0).get("firstName"));
        assertEquals("lastName1", data.get(0).get("lastName"));
        assertEquals("methodOfPay1", data.get(0).get("methodOfPay"));
        assertEquals("phoneNumber1", data.get(0).get("phoneNumber"));
    }

    @Test
    public void testWithLastName() {
        // Initialize variable
        List<PersonEntity> entities = PersonMockData.personList();
        PaginationPersonDto paginationPersonDto = new PaginationPersonDto();
        paginationPersonDto.setLastName("lastName1");

        // Configure method when called
        when(personRepository.findAll()).thenReturn(entities);

        // Call method service
        List<Map<String, Object>> result = personService.find(paginationPersonDto);

        Map<String, Object> response = result.get(0);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        // Asserts
        assertEquals("person", response.get("context"));
        assertEquals(2, response.get("total"));
        // Data 1
        assertEquals(1L, data.get(0).get("id"));
        assertEquals("address1", data.get(0).get("address"));
        assertEquals("city1", data.get(0).get("city"));
        assertEquals("department1", data.get(0).get("department"));
        assertEquals("firstName1", data.get(0).get("firstName"));
        assertEquals("lastName1", data.get(0).get("lastName"));
        assertEquals("methodOfPay1", data.get(0).get("methodOfPay"));
        assertEquals("phoneNumber1", data.get(0).get("phoneNumber"));
    }

    @Test
    public void testWithFlatten(){
        // Initialize variable
        List<PersonEntity> entities = PersonMockData.personListFLatten();
        PaginationPersonDto paginationPersonDto = new PaginationPersonDto();
        paginationPersonDto.setFlatten(true);

        // Configure method when called
        when(personRepository.findAll()).thenReturn(entities);

        // Call method service
        List<Map<String, Object>> result = personService.find(paginationPersonDto);

        Map<String, Object> response = result.get(0);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        // Asserts
        assertEquals("person", response.get("context"));
        assertEquals(2, response.get("total"));
        // Data 1
        assertEquals(1L, data.get(0).get("id"));
        assertEquals("firstName1 lastName1", data.get(0).get("name"));
        // Data 2
        assertEquals(2L, data.get(1).get("id"));
        assertEquals("firstName2 lastName2", data.get(1).get("name"));
    }

    @Test
    public void testErrorAdditionalField(){
        //Initialize variable
        PaginationPersonDto paginationPersonDto = new PaginationPersonDto();

        //Configure method when called
        when(personRepository.findAll()).thenThrow(
            new IllegalArgumentException()
        );

        //Asserts
        assertThrows(IllegalArgumentException.class, ()-> personService.find(paginationPersonDto));
    }

    //NOTE: Test find detail
    
    @Test
    public void testFindDetailSuccessfully(){
        //Initialize variable
        Long id = 1L;
        Optional<PersonEntity> person = PersonMockData.findPersonDetail();

        //Configure method when called
        when(personRepository.findById(id)).thenReturn(person);

        //Call method service
        Optional<PersonEntity> result = personService.findDetail(id);

        //Asserts
        assertEquals(person, result);

        //Verify
        verify(personRepository).findById(id);
    }

    @Test
    public void testFindDetailNotFound(){
        //Initialize variable
        Long id = 1L;

        when(personRepository.findById(id)).thenThrow(
            new NoSuchElementException()
        );

        //Asserts
        assertThrows(NoSuchElementException.class, ()->personService.findDetail(id));

        //Verify
        verify(personRepository).findById(id);
    }
}
