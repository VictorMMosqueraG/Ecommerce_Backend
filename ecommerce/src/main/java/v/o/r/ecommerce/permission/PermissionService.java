package v.o.r.ecommerce.permission;

import java.util.NoSuchElementException;
import java.util.Optional;

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
    
    public Optional<PermissionEntity> findDetail(Long id){
        return this.findByIdOrFail(id);
    }


    //NOTE: methods bases
    public Optional<PermissionEntity> findById(Long id){
        Optional<PermissionEntity> foundPermission = permissionRepository.findById(id);
        return foundPermission;
    }

    public Optional<PermissionEntity> findByIdOrFail(Long id){
        Optional<PermissionEntity> foundPermission=id!=null 
            ? this.findById(id):null;
        if (foundPermission==null || foundPermission.isEmpty()) {
            throw new NoSuchElementException("the Permission with id "+id+ " is not found.");
        }
       
        return foundPermission;
    }
    
}
