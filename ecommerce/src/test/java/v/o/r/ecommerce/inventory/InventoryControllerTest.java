package v.o.r.ecommerce.inventory;

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

import v.o.r.ecommerce.inventories.InventoriesService;
import v.o.r.ecommerce.inventories.entities.InventoryEntity;
import v.o.r.ecommerce.inventory.mockData.InventoryMockData;

@SpringBootTest
@AutoConfigureMockMvc
public class InventoryControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private InventoriesService inventoriesService;

    //NOTE: Test Save Inventory

    @Test
    @WithMockUser(authorities = "Inventory.write.all")
    public void testSaveInventory() throws Exception{
        String json = objectMapper.writeValueAsString(InventoryMockData.createInventoryDto());

        Mockito.when(inventoriesService.save(any())).thenReturn(new InventoryEntity());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/inventories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers
            .status().isCreated()); 
    }

    @Test
    @WithMockUser(authorities = "Inventory.write.all")
    public void testSaveInventoryAlreadyExist() throws Exception{
        String json = objectMapper.writeValueAsString(InventoryMockData.createInventoryDto());

        Mockito.when(inventoriesService.save(any())).thenThrow(new DataIntegrityViolationException("Key=(name)=(name1) already exists.]"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/inventories")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(MockMvcResultMatchers
        .status().isBadRequest()); 
    }

    @Test
    @WithMockUser(authorities = "Inventory.write.all")
    public void testSaveInventoryUnexpected() throws Exception{
        String json = objectMapper.writeValueAsString(InventoryMockData.createInventoryDto());

        Mockito.when(inventoriesService.save(any())).thenThrow(new RuntimeException());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/inventories")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(MockMvcResultMatchers
        .status().isInternalServerError()); 
    }
}
