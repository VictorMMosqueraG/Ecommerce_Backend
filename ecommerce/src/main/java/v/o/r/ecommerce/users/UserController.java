package v.o.r.ecommerce.users;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import v.o.r.ecommerce.common.service.BaseServiceError;
import v.o.r.ecommerce.common.interfaces.users.IUserController;
import v.o.r.ecommerce.users.dto.CreateUserDto;
import v.o.r.ecommerce.users.dto.PaginationUserDto;



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
