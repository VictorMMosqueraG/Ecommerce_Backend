package v.o.r.ecommerce.users;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import v.o.r.ecommerce.common.service.BaseServiceError;
import v.o.r.ecommerce.common.interfaces.users.IUserController;
import v.o.r.ecommerce.users.dto.CreateUserDto;
import v.o.r.ecommerce.users.dto.PaginationUserDto;
import v.o.r.ecommerce.users.dto.UpdateUserDto;
import v.o.r.ecommerce.users.entities.UserEntity;
import org.springframework.web.bind.annotation.PutMapping;






@Validated
@RestController
@Tag(name = "users")
@RequestMapping("user")
public class UserController implements IUserController{

   @Autowired
   private UserService userService;

   @Operation(summary = "Save a users")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201", 
            description = "User created",
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
   @PostMapping("/register") //NOTE: this is for user type normal
    public ResponseEntity<?> saveUser( @RequestBody CreateUserDto createUser) {
        try {
            createUser.role=3L;
            userService.save(createUser);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
             return BaseServiceError.handleException(e);
        }
    }

    @Operation(summary = "Save a user admin")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201", 
            description = "User created",
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
    @PreAuthorize("hasAuthority('User.write.all')")
    @PostMapping("/admin")
    public ResponseEntity<?> saveAdmin( @RequestBody CreateUserDto createUser) {
        try {
            userService.save(createUser);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
             return BaseServiceError.handleException(e);
        }
    }


    @Operation(summary = "Found a user")
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
    @PreAuthorize("hasAuthority('User.read')")
    @GetMapping
    public ResponseEntity<?> find(@ParameterObject @ModelAttribute PaginationUserDto paginationUserDto) {
        try {
            List<Map<String, Object>> foundUser = userService.find(paginationUserDto);
            return ResponseEntity.status(HttpStatus.OK).body(foundUser);
        } catch (Exception e) {
            return BaseServiceError.handleException(e);
        }
    }

    @Operation(summary = "Found Detail a user")
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
    @PreAuthorize("hasAuthority('User.read.all')")
    @GetMapping("/{id}")
    public ResponseEntity<?> findDetail(@PathVariable Long id) {
        try {
            Optional<UserEntity> foundUser = userService.findDetail(id);
            return ResponseEntity.status(HttpStatus.OK).body(foundUser);
        } catch (Exception e) {
            return BaseServiceError.handleException(e);
        }
    }

    @Operation(summary = "Update a users")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "User update",
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
    @PreAuthorize("hasAuthority('User.write.all')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
        @PathVariable Long id, 
        @RequestBody UpdateUserDto updateUserDto 
    ) {
      try {
        UserEntity updateUser = userService.update(id, updateUserDto);
        return ResponseEntity.status(HttpStatus.OK).body(updateUser);
      } catch (Exception e) {
        return BaseServiceError.handleException(e);
      }  
    }   
    
    
    
}
