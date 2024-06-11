package v.o.r.ecommerce.roles;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import v.o.r.ecommerce.common.interfaces.roles.IRoleService;
import v.o.r.ecommerce.permission.PermissionService;
import v.o.r.ecommerce.permission.entities.PermissionEntity;
import v.o.r.ecommerce.roles.dto.CreateRoleDto;
import v.o.r.ecommerce.roles.entities.RoleEntity;
import v.o.r.ecommerce.roles.repositories.RoleRepository;

@Service
public class RoleService implements IRoleService {
    
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionService permissionService;

    public void save(CreateRoleDto createRoleDto){
        RoleEntity role = new RoleEntity();

        role.setName(createRoleDto.name);
        role.setDescription(createRoleDto.description);

       //NOTE: valid if the permission send if exist
        List<PermissionEntity> findPermissions = new ArrayList<>();
        createRoleDto.permission.stream()
            .map(permission -> permissionService.findByIdOrFail(permission))
            .distinct()//ignore item duplicate
            .forEach(permission -> permission.ifPresent(findPermissions::add));
   
        role.setPermission(findPermissions);

        roleRepository.save(role);
    }

    public Optional<RoleEntity> findRoleById(Long id){
        return roleRepository.findById(id);
    }

    public Optional<RoleEntity> findRoleByIdOrFail(Long id){
        Optional<RoleEntity> foundRole = id!=null ? findRoleById(id):null;

        if(foundRole==null || foundRole.isEmpty()){
            throw new NoSuchElementException("Role with id " + id + " not found.");
        }

        return foundRole;
    }
}
