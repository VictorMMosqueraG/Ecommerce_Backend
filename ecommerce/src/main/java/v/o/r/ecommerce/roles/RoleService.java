package v.o.r.ecommerce.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import v.o.r.ecommerce.common.interfaces.roles.IRoleService;
import v.o.r.ecommerce.roles.dto.CreateRoleDto;
import v.o.r.ecommerce.roles.entities.RoleEntity;
import v.o.r.ecommerce.roles.repositories.RoleRepository;

@Service
public class RoleService implements IRoleService {
    
    @Autowired
    private RoleRepository roleRepository;

    public void save(CreateRoleDto createRoleDto){
        RoleEntity role = new RoleEntity();

        role.setName(createRoleDto.name);
        role.setDescription(createRoleDto.description);
        role.setPermission(createRoleDto.permission);

        roleRepository.save(role);
    }
}
