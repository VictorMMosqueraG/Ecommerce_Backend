package v.o.r.ecommerce.role.mockData;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import v.o.r.ecommerce.permission.entities.PermissionEntity;
import v.o.r.ecommerce.permission.mockData.PermissionMockData;
import v.o.r.ecommerce.roles.dto.CreateRoleDto;
import v.o.r.ecommerce.roles.entities.RoleEntity;

public class RoleMockData {

    //NOTE: Save role
    public static CreateRoleDto createRoleDto(){
        CreateRoleDto dto = new CreateRoleDto();
        dto.name = "USER";
        dto.description="create and update all user";
        dto.permission= Arrays.asList(1L, 2L);
        return dto;
    }
    

    public static RoleEntity roleEntity(
        CreateRoleDto dto,
        List<PermissionEntity> permission
    ){
        RoleEntity role = new RoleEntity();
        role.setName(dto.name);
        role.setDescription(dto.description);
        role.setPermission(permission);

        return role;
    }


    //NOTE Find Role
    public static List<RoleEntity> listRole(){
        RoleEntity role1 = new RoleEntity();
        role1.setName("User");
        role1.setDescription("User common");
        role1.setPermission(PermissionMockData.createPermissionEntity(1L));

        RoleEntity role2 = new RoleEntity();
        role2.setName("Super User");
        role2.setDescription("User with someThings");
        role2.setPermission(PermissionMockData.createPermissionEntity(2L));

        List<RoleEntity> roles = Arrays.asList(role1,role2);
        return roles;
    }

    public static List<RoleEntity> listRoleFlatten(){
        RoleEntity role1 = new RoleEntity();
        role1.setId(1L);
        role1.setName("User");
        role1.setDescription("User common");
        List<RoleEntity> role =  Arrays.asList(role1);

        return role;
    }

    //NOTE find detail role
    public static Optional<RoleEntity> findDetail(){
        RoleEntity role = new RoleEntity();
        role.setName("User");
        role.setDescription("User common");
        role.setPermission(PermissionMockData.createPermissionEntity(1L));
     
        return Optional.of(role);
    }



    //NOTE: Methods for external class
    public static RoleEntity createRoleEntity(Long roleId) {
        RoleEntity role = new RoleEntity();
        role.setId(roleId);
        role.setName("USER_ROLE");
        return role;
    }

}
