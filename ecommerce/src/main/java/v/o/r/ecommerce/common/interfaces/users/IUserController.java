package v.o.r.ecommerce.common.interfaces.users;

import org.springframework.http.ResponseEntity;

import v.o.r.ecommerce.users.dto.CreateUserDto;

public interface IUserController {
    public ResponseEntity<?> save(CreateUserDto createUser);    
}
