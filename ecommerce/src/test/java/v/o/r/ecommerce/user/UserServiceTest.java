package v.o.r.ecommerce.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import v.o.r.ecommerce.roles.RoleService;
import v.o.r.ecommerce.roles.entities.RoleEntity;
import v.o.r.ecommerce.user.mockData.userMockData;
import v.o.r.ecommerce.users.UserService;
import v.o.r.ecommerce.users.dto.CreateUserDto;
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

    @Test
    public void testSaveSuccessfully() {
        // Arrange
        CreateUserDto createUserDto = userMockData.createUserDto();
        RoleEntity mockRoleEntity = userMockData.createRoleEntity(createUserDto.role);
        UserEntity mockUserEntity = userMockData.createUserEntity(createUserDto, "encodedPassword", mockRoleEntity);

        // Mocking
        when(hasMap.encode(createUserDto.password)).thenReturn("encodedPassword");
        when(roleService.findRoleByIdOrFail(createUserDto.role)).thenReturn(Optional.of(mockRoleEntity));
        when(userRepository.save(any(UserEntity.class))).thenReturn(mockUserEntity);

        // Act
        UserEntity savedUser = userService.save(createUserDto);

        // Assert
        assertEquals(createUserDto.email, savedUser.getEmail());
        assertEquals("encodedPassword", savedUser.getPassword());
        assertEquals(createUserDto.role, savedUser.getRole().getId());

        // Verify interactions
        verify(hasMap).encode(createUserDto.password);
        verify(roleService).findRoleByIdOrFail(createUserDto.role);
        verify(userRepository).save(any(UserEntity.class));
    }

     @Test
    public void testSaveRoleNotFound() {
        // Arrange
        CreateUserDto createUserDto = userMockData.createUserDto();

        // Mocking
        when(roleService.findRoleByIdOrFail(createUserDto.role)).thenThrow(new NoSuchElementException("Role with id " + createUserDto.role + " not found."));

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> userService.save(createUserDto));

        // Verify interactions
        verify(roleService).findRoleByIdOrFail(createUserDto.role);
    }

    @Test
    public void testSavePasswordIsNull() {
        // Arrange
        CreateUserDto createUserDto = userMockData.createUserDto();
        createUserDto.password = null;
        RoleEntity mockRoleEntity = userMockData.createRoleEntity(createUserDto.role);
        UserEntity mockUserEntity = userMockData.createUserEntity(createUserDto, null, mockRoleEntity);

        // Mocking
        when(roleService.findRoleByIdOrFail(createUserDto.role)).thenReturn(Optional.of(mockRoleEntity));
        when(userRepository.save(any(UserEntity.class))).thenReturn(mockUserEntity);

        // Act
        UserEntity savedUser = userService.save(createUserDto);

        // Assert
        assertEquals(createUserDto.email, savedUser.getEmail());
        assertEquals(null, savedUser.getPassword());
        assertEquals(createUserDto.role, savedUser.getRole().getId());

        // Verify interactions
        verify(roleService).findRoleByIdOrFail(createUserDto.role);
        verify(userRepository).save(any(UserEntity.class));
    }

}
