package v.o.r.ecommerce.permission.mockData;

import java.util.ArrayList;
import java.util.List;

import v.o.r.ecommerce.permission.entities.PermissionEntity;

public class PermissionMockData {
    

    //NOTE: Methods for external class
    public static List<PermissionEntity> createPermissionEntity(Long permissionId){
       // Create a new PermissionEntity object
        PermissionEntity permission = new PermissionEntity();
        permission.setId(permissionId); // Set the ID
        permission.setName("USER.WRITE.ALL");
        permission.setDescription("create and update all user");

        // Create a list and add the permission entity to it
        List<PermissionEntity> permissions = new ArrayList<>();
        permissions.add(permission);

        return permissions; // Return the list of permissions
    }
}
