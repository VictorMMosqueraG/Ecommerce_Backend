package v.o.r.ecommerce.common.interfaces.permission;


import java.util.Optional;
import v.o.r.ecommerce.permission.dto.CreatePermissionDto;
import v.o.r.ecommerce.permission.entities.PermissionEntity;

public interface IPermissionService {
    public PermissionEntity save(CreatePermissionDto createPermission);
    public Optional<PermissionEntity> findById(Long id);
} 
    
