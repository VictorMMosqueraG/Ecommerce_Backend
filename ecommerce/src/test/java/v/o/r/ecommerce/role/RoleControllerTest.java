package v.o.r.ecommerce.role;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.NoSuchElementException;

import javax.management.RuntimeErrorException;

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

import v.o.r.ecommerce.role.mockData.RoleMockData;
import v.o.r.ecommerce.roles.RoleService;
import v.o.r.ecommerce.roles.entities.RoleEntity;

@SpringBootTest
@AutoConfigureMockMvc
public class RoleControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RoleService roleService;

    //NOTE: Test save role

    @Test
    @WithMockUser(authorities = "Role.write.all")
    public void testSaveRole() throws Exception{
        String json = objectMapper.writeValueAsString(RoleMockData.createRoleDto());

        Mockito.when(roleService.save(any())).thenReturn(new RoleEntity());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/roles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers
            .status()
            .isCreated());   
    }

    @Test
    @WithMockUser(authorities = "Role.write.all")
    public void testAlreadyExistRole() throws Exception{
        String json = objectMapper.writeValueAsString(RoleMockData.createRoleDto());

        Mockito.when(roleService.save(any())).thenThrow(
            new DataIntegrityViolationException("Key (name)=(USER) already exists.]")
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/roles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers
            .status().isBadRequest());  
    }

    @Test
    @WithMockUser(authorities = "Role.write.all")
    public void testUnexpectedRole() throws Exception{
        String json = objectMapper.writeValueAsString(RoleMockData.createRoleDto());

        Mockito.when(roleService.save(any())).thenThrow(new RuntimeException());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/roles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers
            .status().isInternalServerError());
    }
    
    //NOTE: Test find role

    @Test
    @WithMockUser(authorities = "Role.read")
    public void testFindRoleSuccessfully()throws Exception{
        String json = objectMapper.writeValueAsString(RoleMockData.listRole());

        Mockito.when(roleService.find(any())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/roles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers
            .status().isOk());
    }

    @Test
    @WithMockUser(authorities = "Role.read")
    public void testFindRoleUnexpected()throws Exception{
        String json = objectMapper.writeValueAsString(RoleMockData.listRole());

        Mockito.when(roleService.find(any())).thenThrow(new RuntimeException());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/roles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers
            .status().isInternalServerError());
    }

    @Test
    @WithMockUser(authorities = "Role.read")
    public void testFindRoleAdditionalParams()throws Exception{
        String json = objectMapper.writeValueAsString(RoleMockData.listRole());

        Mockito.when(roleService.find(any())).thenThrow(
            new IllegalArgumentException("The PaginationRoleDto object cannot have other fields besides 'flatten'.")
        );

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/roles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers
            .status().isBadRequest());
    }

    // NOTE: Test findById role
    
    @Test
    @WithMockUser(authorities = "Role.read.all")
    public void testFindDetailRoleSuccessfully() throws Exception{
        String json = objectMapper.writeValueAsString(RoleMockData.findDetail());

        Mockito.when(roleService.findDetail(anyLong())).thenReturn(RoleMockData.findDetail());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/roles/{id}",1L)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers
            .status().isOk());
    }

    @Test
    @WithMockUser(authorities = "Role.read.all")
    public void testFindDetailRoleUnexpected() throws Exception{
        String json = objectMapper.writeValueAsString(RoleMockData.findDetail());

        Mockito.when(roleService.findDetail(anyLong())).thenThrow(new RuntimeErrorException(null));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/roles/{id}",1L)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers
            .status().isInternalServerError());
    }

    @Test
    @WithMockUser(authorities = "Role.read.all")
    public void testFindDetailRoleNotFound() throws Exception{
        String json = objectMapper.writeValueAsString(RoleMockData.findDetail());

        Mockito.when(roleService.findDetail(anyLong())).thenThrow(new NoSuchElementException("Not found"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/roles/{id}",1L)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers
            .status().isNotFound());
    }

    
}
