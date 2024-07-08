package v.o.r.ecommerce.common.interfaces.roles;

import org.springframework.http.ResponseEntity;

import v.o.r.ecommerce.roles.dto.CreateRoleDto;
import v.o.r.ecommerce.roles.dto.PaginationRoleDto;

public interface IRoleController {
    public ResponseEntity<?> save (CreateRoleDto createRoleDto);
    public ResponseEntity<?> find (PaginationRoleDto paginationRoleDto);
    public ResponseEntity<?> findDetail(Long id);
}
