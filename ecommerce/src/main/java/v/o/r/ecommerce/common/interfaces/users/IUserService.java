package v.o.r.ecommerce.common.interfaces.users;

import v.o.r.ecommerce.users.dto.CreateUserDto;
import v.o.r.ecommerce.users.entities.UserEntity;

public interface IUserService {
  public UserEntity save(CreateUserDto createUser);  
} 
