package v.o.r.ecommerce.permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import v.o.r.ecommerce.common.interfaces.permission.IPermissionService;
import v.o.r.ecommerce.permission.dto.CreatePermission;
import v.o.r.ecommerce.permission.entities.PermissionEntity;
import v.o.r.ecommerce.permission.repositories.PermissionRepository;

@Service
public class PermissionService implements IPermissionService{
    
    @Autowired
    private PermissionRepository permissionRepository;

    public PermissionEntity save(CreatePermission createPermission){
        PermissionEntity permission = new PermissionEntity();
        
        permission.setName(createPermission.name);
        permission.setDescription(createPermission.description);

        permissionRepository.save(permission);
        return permission;
    }
}
