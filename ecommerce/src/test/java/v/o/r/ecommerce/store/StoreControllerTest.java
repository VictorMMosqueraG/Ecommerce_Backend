package v.o.r.ecommerce.store;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

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

import v.o.r.ecommerce.store.mockData.StoreMockData;
import v.o.r.ecommerce.stores.StoresService;
import v.o.r.ecommerce.stores.dto.UpdateStoreDto;
import v.o.r.ecommerce.stores.entities.StoresEntity;

@SpringBootTest
@AutoConfigureMockMvc
public class StoreControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StoresService storesService;

    //NOTE: Test save user
    @Test
    @WithMockUser(authorities = "Store.write.all")
    public void testSaveStore() throws Exception{
        String json = objectMapper.writeValueAsString(StoreMockData.createStoreDto());

        Mockito.when(storesService.save(any())).thenReturn(new StoresEntity());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/stores")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers
            .status()
            .isCreated());   
    }

    @Test
    @WithMockUser(authorities = "Store.write.all")
    public void testAlreadyExist() throws Exception{
        String json = objectMapper.writeValueAsString(StoreMockData.createStoreDto());

        Mockito.when(storesService.save(any()))
            .thenThrow(new DataIntegrityViolationException(
                "Key (name,address,city,department)= (name1,address1,city1,department1) already exists.]"
            ));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/stores")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers
            .status().isBadRequest());
    }

    @Test
    @WithMockUser(authorities = "Store.write.all")
    public void testUnexpected() throws Exception{
        String json = objectMapper.writeValueAsString(StoreMockData.createStoreDto());

        Mockito.when(storesService.save(any())).thenThrow(new RuntimeException());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/stores")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers
            .status().isInternalServerError());
    }

    //NOTE: Test Find Store

    @Test
    @WithMockUser(authorities = "Store.read")
    public void testSuccessfullyFind() throws Exception{
        String json = objectMapper.writeValueAsString(StoreMockData.listStore());

        Mockito.when(storesService.find(any())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/stores")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers
            .status().isOk());
    }

    @Test
    @WithMockUser(authorities = "Store.read")
    public void testUnexpectedFind() throws Exception{
        String json = objectMapper.writeValueAsString(StoreMockData.listStore());

        Mockito.when(storesService.find(any())).thenThrow(new RuntimeException());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/stores")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers
            .status().isInternalServerError());
    }

    @Test
    @WithMockUser(authorities = "Store.read")
    public void testAdditionalParamsStore() throws Exception{
        String json = objectMapper.writeValueAsString(StoreMockData.listStore());

        Mockito.when(storesService.find(any())).thenThrow(
            new IllegalArgumentException("The PaginationStoreDto object cannot have other fields besides 'flatten'.")
        );

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/stores")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(MockMvcResultMatchers
        .status().isBadRequest());
    }

    @Test
    @WithMockUser(authorities = "Store.read")
    public void testSuccessfullyFindWithFlatten() throws Exception{
        String json = objectMapper.writeValueAsString(StoreMockData.listStoreFlatten());

        Mockito.when(storesService.find(any())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/stores")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers
            .status().isOk());
    }

    //NOTE: Test Update Store

    @Test
    @WithMockUser(authorities = "Store.write.all")
    public void testSuccessfullyUpdate() throws Exception{
        String json = objectMapper.writeValueAsString(StoreMockData.updateStoreDto());

        Mockito.when(storesService.update(
                any(Long.class),
                any(UpdateStoreDto.class)
            )
        ).thenReturn(new StoresEntity());

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/stores/{id}",1L)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers
            .status().isOk());
    }

    @Test
    @WithMockUser(authorities = "Store.write.all")
    public void testAlreadyExistUpdate() throws Exception{
        String json = objectMapper.writeValueAsString(StoreMockData.updateStoreDto());

        Mockito.when(storesService.update(anyLong(), any()))
            .thenThrow(new DataIntegrityViolationException(
            "Key (name,address,city,department)= (name1,address1,city1,department1) already exists.]"
        ));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/stores/{id}",1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(MockMvcResultMatchers
        .status().isBadRequest());
    }

    @Test
    @WithMockUser(authorities = "Store.write.all")
    public void testUnexpectedUpdate() throws Exception{
        String json = objectMapper.writeValueAsString(StoreMockData.updateStoreDto());

        Mockito.when(storesService.update(
            anyLong(), any())
        ).thenThrow(new RuntimeException());

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/stores/{id}",1L)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers
            .status().isInternalServerError());
    }
}
