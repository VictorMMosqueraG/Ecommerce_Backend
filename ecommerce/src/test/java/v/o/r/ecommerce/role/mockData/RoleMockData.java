package v.o.r.ecommerce.role.mockData;

import v.o.r.ecommerce.roles.entities.RoleEntity;

public class RoleMockData {

    public static RoleEntity createRoleEntity(Long roleId) {
        RoleEntity role = new RoleEntity();
        role.setId(roleId);
        role.setName("USER_ROLE");
        return role;
    }

}
