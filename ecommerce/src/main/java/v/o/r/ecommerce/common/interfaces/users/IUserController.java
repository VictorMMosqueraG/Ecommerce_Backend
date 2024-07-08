package v.o.r.ecommerce.common.interfaces.users;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import v.o.r.ecommerce.users.dto.CreateUserDto;
import v.o.r.ecommerce.users.dto.PaginationUserDto;
import v.o.r.ecommerce.users.dto.UpdateUserDto;

public interface IUserController {
    public ResponseEntity<?> save(CreateUserDto createUser);

    public ResponseEntity<?> find(
        @ParameterObject 
        @ModelAttribute 
        PaginationUserDto paginationUserDto
    );

    public ResponseEntity<?> findDetail(@PathVariable Long id);
    
    public ResponseEntity<?> update(
        @PathVariable Long id,
        @RequestBody UpdateUserDto updateUserDto
    );
}
