package v.o.r.ecommerce.permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import v.o.r.ecommerce.common.interfaces.permission.IPermissionController;
import v.o.r.ecommerce.common.service.BaseServiceError;
import v.o.r.ecommerce.permission.dto.CreatePermissionDto;
import v.o.r.ecommerce.permission.entities.PermissionEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;


@Validated
@RestController
@RequestMapping("permission")
@Tag(name = "permissions")
public class PermissionController implements IPermissionController {
 
    @Autowired
    private PermissionService permissionService;

    @Operation(summary = "Save a permission")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201", 
            description = "permission created",
            content = @Content(mediaType = "application/json")    
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "Bad request",
            content = @Content(mediaType = "application/json", 
                schema = @Schema(example = "{\"code\": \"BAD_REQUEST\", \"error\": \"Bad request\", \"message\": \"Invalid input data\" }"))
        ),
        @ApiResponse(
            responseCode = "500", 
            description = "Internal Server Error",
            content = @Content(mediaType = "application/json", 
                schema = @Schema(example = "{\"code\": \"UNEXPECTED_ERROR\", \"error\": \"Internal Server Error\", \"message\": \"Unexpected Error\" }"))
        )
    })
    @PreAuthorize("hasAnyAuthority('Permission.write.all', 'Permission.write')")
    @PostMapping
    public ResponseEntity<?> save(@RequestBody CreatePermissionDto createPermission){
        try {
         permissionService.save(createPermission);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return BaseServiceError.handleException(e);
        }
    }

    @Operation(summary = "Found Detail a permission")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Found permission", 
            content = @Content(mediaType = "application/json", 
                schema = @Schema(example = "[\n{\n  \"context\": \"user\",\n  \"total\": 0,\n  \"data\": []\n}\n]"))
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "Bad request",
            content = @Content(mediaType = "application/json", 
                schema = @Schema(example = "{\"code\": \"BAD_REQUEST\", \"error\": \"Bad request\", \"message\": \"Invalid input data\" }"))
        ),
        @ApiResponse(
            responseCode = "500", 
            description = "Internal Server Error",
            content = @Content(mediaType = "application/json", 
                schema = @Schema(example = "{\"code\": \"UNEXPECTED_ERROR\", \"error\": \"Internal Server Error\", \"message\": \"Unexpected Error\" }"))    
        )
    })
    @PreAuthorize("hasAuthority('Permission.read.all')")
    @GetMapping("{id}")
    public ResponseEntity<?> findDetail(@PathVariable Long id) {
        try {
            Optional<PermissionEntity> foundPermission 
                = permissionService.findDetail(id);

            return ResponseEntity.status(HttpStatus.OK).body(foundPermission);
        } catch (Exception e) {
            return BaseServiceError.handleException(e);
        }
    }
    
}
