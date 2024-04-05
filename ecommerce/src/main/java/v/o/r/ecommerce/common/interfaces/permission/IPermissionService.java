package v.o.r.ecommerce.common.interfaces.permission;

import v.o.r.ecommerce.permission.dto.CreatePermission;
import v.o.r.ecommerce.permission.entities.PermissionEntity;

public interface IPermissionService {
    public PermissionEntity save(CreatePermission createPermission);
} 
    
