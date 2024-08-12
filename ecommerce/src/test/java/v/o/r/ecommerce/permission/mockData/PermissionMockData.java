package v.o.r.ecommerce.permission.mockData;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import v.o.r.ecommerce.permission.dto.CreatePermissionDto;
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

    //NOTE: method for save permission
    public static CreatePermissionDto createPermissionDto(){
        CreatePermissionDto dto = new CreatePermissionDto(); 
        dto.name="ROLE.WRITE.ALL";
        dto.description="create and update from module role";

        return dto;
    } 

    public static PermissionEntity permissionEntity(
        CreatePermissionDto createPermissionDto
    ){
        PermissionEntity entity = new PermissionEntity();
        entity.setId(1L);
        entity.setName(createPermissionDto.name);
        entity.setDescription(createPermissionDto.description);

        return entity;
    }

    //NOTE: method for Find Detail Permission
    public static Optional<PermissionEntity> findDetail(){
        PermissionEntity entity = new PermissionEntity();

        entity.setId(1L);
        entity.setName("ROLE.WRITE.ALL");
        entity.setDescription("create and update from module role");

        return Optional.of(entity);
    }
}
