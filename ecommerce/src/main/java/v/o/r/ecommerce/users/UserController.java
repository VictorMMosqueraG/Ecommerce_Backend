package v.o.r.ecommerce.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import v.o.r.ecommerce.common.service.BaseServiceError;
import v.o.r.ecommerce.common.interfaces.users.IUserController;
import v.o.r.ecommerce.users.dto.CreateUserDto;

@Validated
@Controller
@RequestMapping("user")
public class UserController implements IUserController{

   @Autowired
   private UserService userService;

   @PostMapping("/save")
    public ResponseEntity<?> save( @RequestBody CreateUserDto createUser) {
        try {
            userService.save(createUser);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
             return BaseServiceError.handleException(e);
        }
    }
}
