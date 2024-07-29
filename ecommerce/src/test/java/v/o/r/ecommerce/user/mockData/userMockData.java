package v.o.r.ecommerce.user.mockData;

import v.o.r.ecommerce.roles.entities.RoleEntity;
import v.o.r.ecommerce.users.dto.CreateUserDto;
import v.o.r.ecommerce.users.entities.UserEntity;

public class userMockData {
    

    public static CreateUserDto createUserDto() {
        CreateUserDto dto = new CreateUserDto();
        dto.email = "test@gmail.com";
        dto.password = "mySecretPassword";
        dto.role = 1L;
        return dto;
    }

    public static RoleEntity createRoleEntity(Long roleId) {
        RoleEntity role = new RoleEntity();
        role.setId(roleId);
        role.setName("USER_ROLE");
        return role;
    }

    public static UserEntity createUserEntity(CreateUserDto dto, String encodedPassword, RoleEntity role) {
        UserEntity user = new UserEntity();
        user.setEmail(dto.email);
        user.setPassword(encodedPassword);
        user.setRole(role);
        return user;
    }
}
