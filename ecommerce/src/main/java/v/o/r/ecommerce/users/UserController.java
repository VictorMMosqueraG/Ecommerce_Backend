package v.o.r.ecommerce.users;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import v.o.r.ecommerce.users.entities.UserEntity;



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
   @PostMapping("/save")
    public ResponseEntity<?> save( @RequestBody CreateUserDto createUser) {
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
            content = @Content(mediaType = "application/json",schema = @Schema(implementation = UserEntity.class))
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
    @GetMapping ("/find")
    public ResponseEntity<?> find(@ModelAttribute PaginationUserDto paginationUserDto) {
        try {
            List<Map<String, Object>> foundUser = userService.find(paginationUserDto);
            return ResponseEntity.status(HttpStatus.OK).body(foundUser);
        } catch (Exception e) {
            return BaseServiceError.handleException(e);
        }
    }
    
}
