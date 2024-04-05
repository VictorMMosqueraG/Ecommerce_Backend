package v.o.r.ecommerce.common.interfaces.permission;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import v.o.r.ecommerce.permission.dto.CreatePermission;

public interface IPermissionController {
     public ResponseEntity<?> save(@RequestBody CreatePermission createPermission);
}
