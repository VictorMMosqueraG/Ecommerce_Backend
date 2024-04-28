package v.o.r.ecommerce.common.interfaces.roles;

import org.springframework.http.ResponseEntity;

import v.o.r.ecommerce.roles.dto.CreateRoleDto;

public interface IRoleController {
    public ResponseEntity<?> save (CreateRoleDto createRoleDto);
}
