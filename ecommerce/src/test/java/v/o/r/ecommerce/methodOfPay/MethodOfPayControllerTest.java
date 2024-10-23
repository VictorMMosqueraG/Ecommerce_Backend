package v.o.r.ecommerce.methodOfPay;


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

import v.o.r.ecommerce.methodOfPay.entities.MethodOfPayEntity;
import v.o.r.ecommerce.methodOfPay.mockData.MethodOfPayMockData;

@SpringBootTest
@AutoConfigureMockMvc
public class MethodOfPayControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MethodOfPayService methodOfPayService;

    //NOTE: Test save 

    @Test
    @WithMockUser(authorities = "MethodOfPay.write.all")
    public void testSaveMethodPay() throws Exception{
        String json = objectMapper.writeValueAsString(MethodOfPayMockData.createMethodOfPayDto());

        Mockito.when(methodOfPayService.save(any())).thenReturn(new MethodOfPayEntity());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/MethodOfPay")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers
            .status().isCreated()); 
    }

    @Test
    @WithMockUser(authorities = "MethodOfPay.write.all")
    public void testSaveMethodPayAlreadyExist() throws Exception{
        String json = objectMapper.writeValueAsString(MethodOfPayMockData.createMethodOfPayDto());

        Mockito.when(methodOfPayService.save(any())).thenThrow(new DataIntegrityViolationException("Key (name)=(name1) already exist.]"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/MethodOfPay")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers
            .status().isBadRequest()); 
    }

    @Test
    @WithMockUser(authorities = "MethodOfPay.write.all")
    public void testSaveMethodPayUnexpected() throws Exception{
        String json = objectMapper.writeValueAsString(MethodOfPayMockData.createMethodOfPayDto());

        Mockito.when(methodOfPayService.save(any())).thenThrow(new RuntimeException());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/MethodOfPay")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers
            .status().isInternalServerError()); 
    }

    //NOTE: Test Find

    @Test
    @WithMockUser(authorities = "MethodOfPay.read")
    public void testFindMethodPay() throws Exception{
        String json = objectMapper.writeValueAsString(MethodOfPayMockData.listMethodOfPay());

        Mockito.when(methodOfPayService.find(any())).thenReturn(MethodOfPayMockData.expectedFindMethodOfPay());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/MethodOfPay")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().json(json))
            .andExpect(MockMvcResultMatchers
            .status().isOk());
    }

    @Test
    @WithMockUser(authorities = "MethodOfPay.read")
    public void testFindMethodPayAdditionalParams() throws Exception{
        Mockito.when(methodOfPayService.find(any())).thenThrow(new IllegalArgumentException("The PaginationMethodOfPayDto object cannot have other fields besides 'flatten'."));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/MethodOfPay")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers
        .status().isBadRequest());
    }

    @Test
    @WithMockUser(authorities = "MethodOfPay.read")
    public void testFindMethodPayUnexpected() throws Exception{
        Mockito.when(methodOfPayService.find(any())).thenThrow(new RuntimeException());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/MethodOfPay")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers
            .status().isInternalServerError());
    }
}
