package v.o.r.ecommerce.user.mockData;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import v.o.r.ecommerce.role.mockData.RoleMockData;
import v.o.r.ecommerce.roles.entities.RoleEntity;
import v.o.r.ecommerce.users.dto.CreateUserDto;
import v.o.r.ecommerce.users.dto.UpdateUserDto;
import v.o.r.ecommerce.users.entities.UserEntity;

public class userMockData {
    
    //NOTE: Create user
    public static CreateUserDto createUserDto() {
        CreateUserDto dto = new CreateUserDto();
        dto.email = "test@gmail.com";
        dto.password = "mySecretPassword";
        dto.role = 1L;
        return dto;
    }

    public static UserEntity createUserEntity(
        CreateUserDto dto, 
        String encodedPassword, 
        RoleEntity role
    ) {
        UserEntity user = new UserEntity();
        user.setEmail(dto.email);
        user.setPassword(encodedPassword);
        user.setRole(role);
        return user;
    }

    //NOTE: find user
    public static List<UserEntity> listUser(){
        UserEntity user1 = new UserEntity();
        user1.setId(1L);
        user1.setEmail("test@example.com");
        user1.setRole(RoleMockData.createRoleEntity(1L));

        UserEntity user2 = new UserEntity();
        user2.setId(2L);
        user2.setEmail("example@example.com");
        user2.setRole(RoleMockData.createRoleEntity(2L));
        List<UserEntity> users = Arrays.asList(user1, user2);

        return users;
    }

    //FIX: this test is invalid, flatten is only id and name
    public static List<UserEntity> listUserFlatten(){
        UserEntity user1 = new UserEntity();
        user1.setId(1L);
        user1.setEmail("test@example.com");
        List<UserEntity> users = Arrays.asList(user1);

        return users;
    }

    //NOTE: find detail user
    public static Optional<UserEntity> findUserDetail(){
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setRole(RoleMockData.createRoleEntity(1L));

        return Optional.of(user);
    }

    //NOTE: update user
    public static UpdateUserDto updateUserDto() {
        UpdateUserDto dto = new UpdateUserDto();
        dto.email = "test@gmail.com";
        dto.password = "encodedPassword";
        dto.role = 1L;
        return dto;
    }

    public static UserEntity updateUserEntity(
        UpdateUserDto dto, 
        String encodedPassword, 
        RoleEntity role
    ){
        UserEntity user = new UserEntity();
        user.setEmail(dto.email);
        user.setPassword(encodedPassword);
        user.setRole(role);
        return user;
    }
}
