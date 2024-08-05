package v.o.r.ecommerce.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import v.o.r.ecommerce.role.mockData.RoleMockData;
import v.o.r.ecommerce.roles.RoleService;
import v.o.r.ecommerce.roles.entities.RoleEntity;
import v.o.r.ecommerce.user.mockData.userMockData;
import v.o.r.ecommerce.users.UserService;
import v.o.r.ecommerce.users.dto.CreateUserDto;
import v.o.r.ecommerce.users.dto.PaginationUserDto;
import v.o.r.ecommerce.users.entities.UserEntity;
import v.o.r.ecommerce.users.repositories.UserRepository;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder hasMap;

    @Mock
    private RoleService roleService;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //NOTE: Save test
    @Test
    public void testSaveSuccessfully() {
        
        //Initialize variables
        CreateUserDto createUserDto = userMockData.createUserDto();
        RoleEntity mockRoleEntity = RoleMockData.createRoleEntity(createUserDto.role);
        UserEntity mockUserEntity = userMockData.createUserEntity(createUserDto, "encodedPassword", mockRoleEntity);

        //Configure when call methods
        when(hasMap.encode(createUserDto.password)).thenReturn("encodedPassword");
        when(roleService.findRoleByIdOrFail(createUserDto.role)).thenReturn(Optional.of(mockRoleEntity));
        when(userRepository.save(any(UserEntity.class))).thenReturn(mockUserEntity);

        //Call method service
        UserEntity savedUser = userService.save(createUserDto);

        //Assert
        assertEquals(createUserDto.email, savedUser.getEmail());
        assertEquals("encodedPassword", savedUser.getPassword());
        assertEquals(createUserDto.role, savedUser.getRole().getId());

        //Verify interactions if the class was call with method and correct data
        verify(hasMap).encode(createUserDto.password);
        verify(roleService).findRoleByIdOrFail(createUserDto.role);
        verify(userRepository).save(any(UserEntity.class));
    }

     @Test
    public void testSaveRoleNotFound() {
        //Initialize variables
        CreateUserDto createUserDto = userMockData.createUserDto();

        //Configure method when called
        when(roleService.findRoleByIdOrFail(createUserDto.role))
            .thenThrow(new NoSuchElementException("Role with id " + createUserDto.role + " not found."));

        //Assert
        assertThrows(NoSuchElementException.class, () -> userService.save(createUserDto));

        // Verify interactions
        verify(roleService).findRoleByIdOrFail(createUserDto.role);
    }

    @Test
    public void testSavePasswordIsNull() {
        //Initialize variables
        CreateUserDto createUserDto = userMockData.createUserDto();
        RoleEntity mockRoleEntity = RoleMockData.createRoleEntity(createUserDto.role);
        UserEntity mockUserEntity = userMockData.createUserEntity(createUserDto, null, mockRoleEntity);

        //Configure method when called 
        when(roleService.findRoleByIdOrFail(createUserDto.role)).thenReturn(Optional.of(mockRoleEntity));
        when(userRepository.save(any(UserEntity.class))).thenReturn(mockUserEntity);

        //Call method service
        UserEntity savedUser = userService.save(createUserDto);

        //Assert
        assertEquals(createUserDto.email, savedUser.getEmail());
        assertEquals(null, savedUser.getPassword());
        assertEquals(createUserDto.role, savedUser.getRole().getId());

        // Verify interactions
        verify(roleService).findRoleByIdOrFail(createUserDto.role);
        verify(userRepository).save(any(UserEntity.class));
    }

    //NOTE: Find test
    @Test
    public void testFindSuccessfully(){
        //Initialize variable
        PaginationUserDto paginationUserDto = new PaginationUserDto();
        List<UserEntity> users = userMockData.listUser();

        //Configure method when called
        when(userRepository.findAll()).thenReturn(users);

        //Call method service
        List<Map<String, Object>> result = userService.find(paginationUserDto);

        
        Map<String, Object> response = result.get(0);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        assertEquals("user", response.get("context"));
        assertEquals(2, data.size());

        verify(userRepository).findAll();
    }

    @Test
    public void testFindWithFlatten() {
        //Initialize variables pagination and user
        List<UserEntity> users = userMockData.listUserFlatten();
        PaginationUserDto paginationUserDto = new PaginationUserDto();
        paginationUserDto.setFlatten(true);


        //Configure method when called
        when(userRepository.findAll()).thenReturn(users);

        //Call method service
        List<Map<String, Object>> result = userService.find(paginationUserDto);

        Map<String, Object> response = result.get(0);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        //asserts
        assertEquals("user", response.get("context"));
        assertEquals(1, response.get("total"));
        assertEquals(1, data.size());
        assertEquals(1L, data.get(0).get("id"));
        assertEquals("test@example.com", data.get(0).get("email"));

        //Verify
        verify(userRepository).findAll();
    }

    @Test
    public void testFindWithFlattenAndAdditionalFields(){
        //Initialize variables
        PaginationUserDto paginationUserDto = new PaginationUserDto();
        
        //Configure method when called
        when(userRepository.findAll())
            .thenThrow(new IllegalArgumentException(
                "The PaginationUserDto object cannot have other fields besides 'flatten'."
                )
            );

        //Assert
        assertThrows(
            IllegalArgumentException.class, 
                () -> userService.find(paginationUserDto)
        );

        verify(userRepository).findAll();
    }

    @Test
    public void testFindWithLimit() {
        //Initialize variables
        List<UserEntity> users = userMockData.listUser();
        PaginationUserDto paginationUserDto = new PaginationUserDto();
        paginationUserDto.setLimit(1);

        //Configure method when called
        when(userRepository.findAll()).thenReturn(users);

        //Call method service
        List<Map<String, Object>> result = userService.find(paginationUserDto);

        Map<String, Object> response = result.get(0);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");
        
        // Assert
        assertEquals(1, data.size());
        assertEquals(2L, data.get(0).get("id"));
        assertEquals("example@example.com", data.get(0).get("email"));
    }

    @Test
    public void testFindWithOffset() {
        //Initialize variables
        List<UserEntity> users = userMockData.listUser();
        PaginationUserDto paginationUserDto = new PaginationUserDto();
        paginationUserDto.setOffset(1);

        //Configure method when called
        when(userRepository.findAll()).thenReturn(users);

        //Call method service
        List<Map<String, Object>> result = userService.find(paginationUserDto);

        Map<String, Object> response = result.get(0);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");
        
        // Assert
        assertEquals(1, data.size());
        assertEquals(1L, data.get(0).get("id"));
        assertEquals("test@example.com", data.get(0).get("email"));
    }
   
    @Test
    public void testFindWithSortOrder(){
        //Initialize variables
        List<UserEntity> users = userMockData.listUser();
        PaginationUserDto paginationUserDto = new PaginationUserDto();
        paginationUserDto.setSortOrder("DESC");

        //Configure method when called
        when(userRepository.findAll()).thenReturn(users);

        List<Map<String, Object>>  result = userService.find(paginationUserDto);

        Map<String, Object> response = result.get(0);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        //Assert
        assertEquals(2, data.size());
        assertEquals("user", response.get("context"));
        assertEquals(1L, data.get(0).get("id"));
        assertEquals("test@example.com", data.get(0).get("email"));

        //Verify
        verify(userRepository).findAll();
    }

    @Test
    public void testFIndWithFilterEmail(){
        //Initialize variable
        List<UserEntity> users = userMockData.listUser();
        PaginationUserDto paginationUserDto = new PaginationUserDto();
        paginationUserDto.setEmail("test@example.com");

        //Configure method when called
        when(userRepository.findAll()).thenReturn(users);

        List<Map<String, Object>>  result = userService.find(paginationUserDto);

        Map<String, Object> response = result.get(0);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        //Asserts
        assertEquals("user", response.get("context"));
        assertEquals(1, data.size());
        assertEquals(1L, data.get(0).get("id"));
        assertEquals("test@example.com", data.get(0).get("email"));

          //Verify
          verify(userRepository).findAll();
    }

    @Test
    public void testFIndWithFilterRole(){
        //Initialize variable
        List<UserEntity> users = userMockData.listUser();
        PaginationUserDto paginationUserDto = new PaginationUserDto();
        paginationUserDto.setRole("USER_ROLE");

        //Configure method when called
        when(userRepository.findAll()).thenReturn(users);

        List<Map<String, Object>>  result = userService.find(paginationUserDto);

        Map<String, Object> response = result.get(0);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        //Asserts
        assertEquals("user", response.get("context"));
        assertEquals(2, data.size());

        //Verify
        verify(userRepository).findAll();
    }

    //NOTE: Find detail Test
    @Test
    public void testFindDetailUser(){
        //Initialize variable
        Long id = 1L;
        Optional<UserEntity> user = userMockData.findUserDetail();

        //Configure method when called
        when(userRepository.findById(id)).thenReturn(user);

        //Call method service
        Optional<UserEntity> result = userService.findDetail(id);

        //Assert
        assertEquals(user, result);

        //Verify
        verify(userRepository).findById(id);
    }

    @Test
    public void testFindDetailUserNotFound(){
        //Initialize variable
        Long id = 1L;
 
        //Configure method when called
        when(userRepository.findById(id))
            .thenThrow(
                new NoSuchElementException("User with id " + id + " not found.")
            );

         //Assert
        assertThrows(
            NoSuchElementException.class, 
            () -> userService.findDetail(id)
        );

        //Verify
        verify(userRepository).findById(id);
    }
}
