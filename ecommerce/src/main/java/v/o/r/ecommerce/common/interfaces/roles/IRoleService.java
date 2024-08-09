package v.o.r.ecommerce.common.interfaces.roles;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import v.o.r.ecommerce.roles.dto.CreateRoleDto;
import v.o.r.ecommerce.roles.dto.PaginationRoleDto;
import v.o.r.ecommerce.roles.entities.RoleEntity;

public interface IRoleService {
    public RoleEntity save(CreateRoleDto createRoleDto);
    public List<Map<String,Object>> find(PaginationRoleDto paginationRoleDto);
    public Optional<RoleEntity> findDetail(Long id);
}
