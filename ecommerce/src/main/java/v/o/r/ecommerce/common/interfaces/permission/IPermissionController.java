package v.o.r.ecommerce.common.interfaces.permission;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import v.o.r.ecommerce.permission.dto.CreatePermissionDto;

public interface IPermissionController {
     public ResponseEntity<?> save(@RequestBody CreatePermissionDto createPermission);
     public ResponseEntity<?> findDetail(@PathVariable Long id);
}
