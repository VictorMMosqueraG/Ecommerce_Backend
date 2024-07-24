package v.o.r.ecommerce.roles;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import v.o.r.ecommerce.common.interfaces.roles.IRoleController;
import v.o.r.ecommerce.common.service.BaseServiceError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import v.o.r.ecommerce.roles.dto.CreateRoleDto;
import v.o.r.ecommerce.roles.dto.PaginationRoleDto;
import v.o.r.ecommerce.roles.entities.RoleEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;




@Validated
@RestController
@Tag(name = "roles")
@RequestMapping("roles")
public class RoleController implements IRoleController{

    @Autowired
    private RoleService roleService;

    
    @Operation(summary = "Save a role")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201", 
            description = "role created",
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
    @PreAuthorize("hasAnyAuthority('Role.write.all','Role.write')")
    @PostMapping
    public ResponseEntity<?> save (@RequestBody CreateRoleDto createRoleDto){
        try {
            roleService.save(createRoleDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return BaseServiceError.handleException(e);
        }
    }


    @Operation(summary = "Found a role")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Found user", 
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
    @PreAuthorize("hasAuthority('Role.read')")
    @GetMapping
    public ResponseEntity<?> find (@ParameterObject @ModelAttribute PaginationRoleDto paginationRoleDto) {
        try {
            List<Map<String, Object>> foundRole = roleService.find(paginationRoleDto);
            return ResponseEntity.status(HttpStatus.OK).body(foundRole);
        } catch (Exception e) {
            return BaseServiceError.handleException(e);
        }
    }


    @Operation(summary = "Found a role by id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Found user", 
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
    @PreAuthorize("hasAuthority('Role.read.all')")
    @GetMapping("/{id}")
    public ResponseEntity<?> findDetail(@PathVariable Long id) {
        try {
            Optional<RoleEntity> foundRole = roleService.findDetail(id);
            return ResponseEntity.status(HttpStatus.OK).body(foundRole);
        } catch (Exception e) {
            return BaseServiceError.handleException(e);
        }
    }
    
}
