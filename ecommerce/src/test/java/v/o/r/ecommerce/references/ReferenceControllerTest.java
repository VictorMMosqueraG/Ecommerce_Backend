package v.o.r.ecommerce.references;

import static org.mockito.ArgumentMatchers.any;

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

import v.o.r.ecommerce.references.entities.ReferenceEntity;
import v.o.r.ecommerce.references.mockData.ReferenceMockData;

@SpringBootTest
@AutoConfigureMockMvc
public class ReferenceControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ReferenceService referenceService;

    //NOTE: Test save reference

    @Test
    @WithMockUser(authorities = "Reference.write.all")
    public void testSaveReferenceSuccessfully() throws Exception{
        String json = objectMapper.writeValueAsString(ReferenceMockData.createReferenceDto());

        Mockito.when(referenceService.save(any())).thenReturn(new ReferenceEntity());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/references")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers
            .status()
            .isCreated()); 
    }

    @Test
    @WithMockUser(authorities = "Reference.write.all")
    public void testReferenceAlreadyExist() throws Exception{
        String json = objectMapper.writeValueAsString(ReferenceMockData.createReferenceDto());

        Mockito.when(referenceService.save(any())).thenThrow(
            new DataIntegrityViolationException("Key (name)=(Reference) already exists.]")
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/references")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers
            .status()
            .isBadRequest()); 
    }    

    @Test
    @WithMockUser(authorities = "Reference.write.all")
    public void testReferenceUnexpectedError() throws Exception{
        String json = objectMapper.writeValueAsString(ReferenceMockData.createReferenceDto());

        Mockito.when(referenceService.save(any())).thenThrow(new RuntimeException());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/references")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers
            .status()
            .isInternalServerError()); 
    }
}
