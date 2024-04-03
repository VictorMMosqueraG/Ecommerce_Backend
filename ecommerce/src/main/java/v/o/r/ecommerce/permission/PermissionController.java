package v.o.r.ecommerce.permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.v3.oas.annotations.tags.Tag;
import v.o.r.ecommerce.common.interfaces.permission.IPermissionController;
import v.o.r.ecommerce.common.service.BaseServiceError;
import v.o.r.ecommerce.permission.dto.CreatePermission;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Validated
@RestController
@RequestMapping("permission")
@Tag(name = "permissions")
public class PermissionController implements IPermissionController {
 
    @Autowired
    private PermissionService permissionService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody CreatePermission createPermission){
        try {
         permissionService.save(createPermission);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return BaseServiceError.handleException(e);
        }
    }
}
