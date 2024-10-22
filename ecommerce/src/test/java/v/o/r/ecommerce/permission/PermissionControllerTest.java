package v.o.r.ecommerce.permission;

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

import v.o.r.ecommerce.permission.entities.PermissionEntity;
import v.o.r.ecommerce.permission.mockData.PermissionMockData;

@SpringBootTest
@AutoConfigureMockMvc
public class PermissionControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PermissionService permissionService;


    //NOTE: Test Save Permission

    @Test
    @WithMockUser(authorities = "Permission.write.all")
    public void testSavePermissionSuccessfully() throws Exception{
        String json = objectMapper.writeValueAsString(PermissionMockData.createPermissionDto());

        Mockito.when(permissionService.save(any())).thenReturn(new PermissionEntity());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/permission")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers
            .status()
            .isCreated()); 
    }

    @Test
    @WithMockUser(authorities = "Permission.write.all")
    public void testSavePermissionAlreadyExist() throws Exception{
        String json = objectMapper.writeValueAsString(PermissionMockData.createPermissionDto());

        Mockito.when(permissionService.save(any())).thenThrow(new DataIntegrityViolationException("Key (name)=(name1) already exist.]"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/permission")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers
            .status()
            .isBadRequest()); 
    }

    @Test
    @WithMockUser(authorities = "Permission.write.all")
    public void testSavePermissionUnexpected() throws Exception{
        String json = objectMapper.writeValueAsString(PermissionMockData.createPermissionDto());

        Mockito.when(permissionService.save(any())).thenThrow(new RuntimeException());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/permission")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers
            .status()
            .isInternalServerError()); 
    }

    //NOTE: Test Find Detail Permission

    @Test
    @WithMockUser(authorities = "Permission.read.all")
    public void testFindDetailPermissionSuccessfully() throws Exception{
        Mockito.when(permissionService.findDetail(anyLong())).thenReturn(PermissionMockData.findDetail());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/permission/{id}",1L)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers
            .status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("ROLE.WRITE.ALL"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("create and update from module role")); 
    }

    @Test
    @WithMockUser(authorities = "Permission.read.all")
    public void testFindDetailPermissionNotFound() throws Exception{
        Mockito.when(permissionService.findDetail(anyLong())).thenThrow(new NoSuchElementException("Not found"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/permission/{id}",1L)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers
            .status().isNotFound());
    }

    @Test
    @WithMockUser(authorities = "Permission.read.all")
    public void testFindDetailPermissionUnexpected() throws Exception{
        Mockito.when(permissionService.findDetail(anyLong())).thenThrow(new RuntimeException());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/permission/{id}",1L)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers
            .status().isInternalServerError());
    }
}
