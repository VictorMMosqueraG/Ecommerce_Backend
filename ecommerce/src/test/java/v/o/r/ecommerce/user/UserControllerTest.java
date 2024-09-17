package v.o.r.ecommerce.user;


import static org.mockito.ArgumentMatchers.any;

import java.util.Collections;
import java.util.Set;
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

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import v.o.r.ecommerce.user.mockData.userMockData;
import v.o.r.ecommerce.users.UserService;
import v.o.r.ecommerce.users.dto.UpdateUserDto;
import v.o.r.ecommerce.users.entities.UserEntity;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    //NOTE: Test save user 
    @Test
    public void testSaveUserSuccessfully() throws Exception {
        String userJson = objectMapper.writeValueAsString(userMockData.createUserDto());

        Mockito.when(userService.save(any())).thenReturn(new UserEntity());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson))
            .andExpect(MockMvcResultMatchers
            .status()
            .isCreated());         
    }

    @Test
    public void testSaveUserDataIntegrityViolation() throws Exception {
        String userJson = objectMapper.writeValueAsString(userMockData.createUserDto());

        Mockito.when(userService.save(any()))
            .thenThrow(
                new DataIntegrityViolationException(
                    "Key (email)=(test3@gmail.com) already exists.]")
                );

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(MockMvcResultMatchers.content()
                .json("{\"message\":\"Key (email)=(test3@gmail.com) already exists\",\"status\":400,\"code\":\"ALREADY_EXIST\"}"));
    }

    @Test
    public void testSaveUserMissingField() throws Exception {
        String userJson = objectMapper.writeValueAsString(userMockData.createUserDto());

        ConstraintViolation<?> mockViolation = Mockito.mock(ConstraintViolation.class);
        Mockito.when(mockViolation.getMessage()).thenReturn("Email cannot be blank");
        Mockito.when(mockViolation.getPropertyPath()).thenReturn(null);

        Set<ConstraintViolation<?>> violations = Collections.singleton(mockViolation);
    
        Mockito.when(userService.save(any()))
            .thenThrow(
                new ConstraintViolationException(null,violations)//COMEBACK:create class with errors 
            );

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(MockMvcResultMatchers.content()
                .json("{\"message\":\"Email cannot be blank\",\"status\":400,\"code\":\"MISSING_FILED\"}"));
    }

    @Test
    public void testSaveUserUnexpectedError() throws Exception {
        String userJson = objectMapper.writeValueAsString(userMockData.createUserDto());

        Mockito.when(userService.save(any())).thenThrow(
            new RuntimeException("Unexpected error")
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson))
            .andExpect(MockMvcResultMatchers.status().isInternalServerError()) 
            .andExpect(MockMvcResultMatchers.content()
                .json("{\"message\":\"Unexpected Error\",\"status\":500,\"code\":\"INTERNAL_SERVER_ERROR\"}"));    
    }



    //NOTE: test save user but with admin
    @Test
    @WithMockUser(authorities = "User.write.all")
    public void testSaveUserAdmin() throws Exception{
        String userJson = objectMapper.writeValueAsString(userMockData.createUserDto());

        Mockito.when(userService.save(any())).thenReturn(new UserEntity());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/admin")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson))
            .andExpect(MockMvcResultMatchers
            .status()
            .isCreated());   
    }

    @Test
    @WithMockUser(authorities = "User.write.all")
    public void testSaveUserDataIntegrityViolationAdmin() throws Exception {
        String userJson = objectMapper.writeValueAsString(userMockData.createUserDto());

        Mockito.when(userService.save(any()))
            .thenThrow(
                new DataIntegrityViolationException(
                    "Key (email)=(test3@gmail.com) already exists.]")
                );

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/admin")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(MockMvcResultMatchers.content()
                .json("{\"message\":\"Key (email)=(test3@gmail.com) already exists\",\"status\":400,\"code\":\"ALREADY_EXIST\"}"));
    }

    @Test
    @WithMockUser(authorities = "User.write.all")
    public void testSaveUserMissingFieldAdmin() throws Exception {
        String userJson = objectMapper.writeValueAsString(userMockData.createUserDto());

        ConstraintViolation<?> mockViolation = Mockito.mock(ConstraintViolation.class);
        Mockito.when(mockViolation.getMessage()).thenReturn("Email cannot be blank");
        Mockito.when(mockViolation.getPropertyPath()).thenReturn(null);

        Set<ConstraintViolation<?>> violations = Collections.singleton(mockViolation);
    
        Mockito.when(userService.save(any()))
            .thenThrow(
                new ConstraintViolationException(null,violations)//COMEBACK:create class with errors 
            );

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/admin")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(MockMvcResultMatchers.content()
                .json("{\"message\":\"Email cannot be blank\",\"status\":400,\"code\":\"MISSING_FILED\"}"));
    }

    @Test
    @WithMockUser(authorities = "User.write.all")
    public void testSaveUserAdminUnexpectedError() throws Exception {
        String userJson = objectMapper.writeValueAsString(userMockData.createUserDto());

        Mockito.when(userService.save(any())).thenThrow(
            new RuntimeException("Unexpected error")
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/admin")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson))
            .andExpect(MockMvcResultMatchers.status().isInternalServerError()) 
            .andExpect(MockMvcResultMatchers.content()
                .json("{\"message\":\"Unexpected Error\",\"status\":500,\"code\":\"INTERNAL_SERVER_ERROR\"}"));    
    }

    //NOTE: Test find user
    @Test
    @WithMockUser(authorities = "User.read")
    public void testFindUser() throws Exception{
        String userJson = objectMapper.writeValueAsString(userMockData.listUser());
        Mockito.when(userService.find(any())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson))
            .andExpect(MockMvcResultMatchers
            .status()
            .isOk());   
    }

    @Test
    @WithMockUser(authorities = "User.read")
    public void testFindUserUnexpectedError() throws Exception{
        Mockito.when(userService.find(any())).thenThrow(
            new RuntimeException("Unexpected error")
        );

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isInternalServerError()) 
        .andExpect(MockMvcResultMatchers.content()
            .json("{\"message\":\"Unexpected Error\",\"status\":500,\"code\":\"INTERNAL_SERVER_ERROR\"}"));
    }

    @Test
    @WithMockUser(authorities = "User.read")
    public void testFindUserAdditionalParams() throws Exception{
        Mockito.when(userService.find(any())).thenThrow(
            new IllegalArgumentException("The PaginationUserDto object cannot have other fields besides 'flatten'.")
        );

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest()) 
        .andExpect(MockMvcResultMatchers.content()
            .json("{\"message\":\"The PaginationUserDto object cannot have other fields besides 'flatten'.\",\"status\":400,\"code\":\"ERROR_PARAMS\"}"));
    }


    //NOTE: Test find detail user
    @Test
    @WithMockUser(authorities = "User.read.all")
    public void testFindUserDetail() throws Exception {
        Mockito.when(userService.findDetail(any(Long.class))).thenReturn(userMockData.findUserDetail());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/{id}", 1L)  
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L)) 
            .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("test@example.com")) 
            .andExpect(MockMvcResultMatchers.jsonPath("$.role.name").value("USER_ROLE")); 
    }

    @Test
    @WithMockUser(authorities = "User.read.all")
    public void testFindUserDetailNotFound() throws Exception {
        Mockito.when(userService.findDetail(any(Long.class))).thenThrow(new NoSuchElementException("Not found"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/{id}", 1L)  
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @WithMockUser(authorities = "User.read.all")
    public void testFindUserDetailUnexpectedError() throws Exception {
        Mockito.when(userService.findDetail(any(Long.class)))
           .thenThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/{id}", 1L)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isInternalServerError()) 
            .andExpect(MockMvcResultMatchers.content()
                .json("{\"message\":\"Unexpected Error\",\"status\":500,\"code\":\"INTERNAL_SERVER_ERROR\"}"));
    }   


    //NOTE: Test user update
  
    @Test
    @WithMockUser(authorities = "User.write.all")
    public void testUpdateUser() throws Exception {
        String userJson = objectMapper.writeValueAsString(userMockData.updateUserDto());

        Mockito.when(userService.update(
            any(Long.class), 
            any(UpdateUserDto.class)))
        .thenReturn(new UserEntity());

        mockMvc.perform(MockMvcRequestBuilders.put(
        "/api/v1/user/{id}",
             1L
        )

        .contentType(MediaType.APPLICATION_JSON)
        .content(userJson))
        .andExpect(MockMvcResultMatchers.status().isOk()); 
    }

    @Test
    @WithMockUser(authorities = "User.write.all")
    public void testUpdateUserUnexpectedError() throws Exception {
        String userJson = objectMapper.writeValueAsString(userMockData.updateUserDto());

        Mockito.when(userService.update(
            any(Long.class), 
            any(UpdateUserDto.class))
        ).thenThrow(
            new RuntimeException("Unexpected error")
        );

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/user/{id}", 1L)
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson))
            .andExpect(MockMvcResultMatchers.status().isInternalServerError())  
            .andExpect(MockMvcResultMatchers.content()
                .json("{\"message\":\"Unexpected Error\",\"status\":500,\"code\":\"INTERNAL_SERVER_ERROR\"}"));  
    }


}
