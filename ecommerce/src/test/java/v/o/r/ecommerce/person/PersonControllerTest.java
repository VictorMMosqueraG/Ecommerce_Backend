package v.o.r.ecommerce.person;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import v.o.r.ecommerce.person.mockData.PersonMockData;
import v.o.r.ecommerce.persons.PersonService;
import v.o.r.ecommerce.persons.entities.PersonEntity;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PersonService personService;


    //NOTE: Test Save Person

    @Test
    @WithMockUser(authorities = "Person.write.all")
    public void testSavePersonSuccessfully() throws Exception{
        String json = objectMapper.writeValueAsString(PersonMockData.createPersonDto());

        Mockito.when(personService.save(any())).thenReturn(new PersonEntity());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/person")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers
            .status()
            .isCreated()); 
    }

    @Test
    @WithMockUser(authorities = "Person.write.all")
    public void testSavePersonAlreadyExist() throws Exception{
        String json = objectMapper.writeValueAsString(PersonMockData.createPersonDto());

        Mockito.when(personService.save(any())).thenThrow(new DataIntegrityViolationException("Key (id)=(1) already exists.]"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/person")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers
            .status()
            .isBadRequest()); 
    }

    @Test
    @WithMockUser(authorities = "Person.write.all")
    public void testSavePersonUnexpected() throws Exception{
        String json = objectMapper.writeValueAsString(PersonMockData.createPersonDto());

        Mockito.when(personService.save(any())).thenThrow(new RuntimeException());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/person")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers
            .status()
            .isInternalServerError()); 
    }

    //NOTE: Test Find Person

    @Test
    @WithMockUser(authorities = "Person.read")
    public void testFindPersonSuccessfully() throws Exception{
        String json = objectMapper.writeValueAsString(PersonMockData.personList());

        Mockito.when(personService.find(any())).thenReturn(PersonMockData.expectedFindPerson());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/person")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().json(json))
            .andExpect(MockMvcResultMatchers
            .status()
            .isOk()); 
    }

    @Test
    @WithMockUser(authorities = "Person.read")
    public void testFindPersonUnexpected() throws Exception{
        Mockito.when(personService.find(any())).thenThrow(new RuntimeException());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/person")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers
        .status()
        .isInternalServerError());
    }

    @Test
    @WithMockUser(authorities = "Person.read")
    public void testFindPersonAdditionalParams()throws Exception{
        Mockito.when(personService.find(any())).thenThrow(new IllegalArgumentException("The PaginationPersonDto object cannot have other fields besides 'flatten'."));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/person")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers
            .status()
            .isBadRequest()); 
    }

    //NOTE: Test Find Detail

    @Test
    @WithMockUser(authorities = "Person.read.all")
    public void testFindPersonDetailSuccessfully() throws Exception{

        Mockito.when(personService.findDetail(anyLong())).thenReturn(PersonMockData.findPersonDetail());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/person/{id}", 1L)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
            .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("address1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.city").value("city1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.department").value("department1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("firstName1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("lastName1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.methodOfPay").value("methodOfPay1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("phoneNumber1"));
    }

    @Test
    @WithMockUser(authorities = "Person.read.all")
    public void testFindPersonNotFound() throws Exception{

        Mockito.when(personService.findDetail(anyLong())).thenThrow(new NoSuchElementException("Not found"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/person/{id}", 1L)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @WithMockUser(authorities = "Person.read.all")
    public void testFindDetailPersonUnexpected() throws Exception{
        Mockito.when(personService.findDetail(anyLong())).thenThrow(new RuntimeException());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/person/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }
}
 