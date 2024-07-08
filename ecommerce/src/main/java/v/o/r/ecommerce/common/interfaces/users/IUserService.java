package v.o.r.ecommerce.common.interfaces.users;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import v.o.r.ecommerce.users.dto.CreateUserDto;
import v.o.r.ecommerce.users.dto.PaginationUserDto;
import v.o.r.ecommerce.users.dto.UpdateUserDto;
import v.o.r.ecommerce.users.entities.UserEntity;

public interface IUserService {
  public UserEntity save(CreateUserDto createUser);  
  public List<Map<String,Object>> find(PaginationUserDto paginationUserDto);
  public Optional<UserEntity> findDetail(Long id);
  public UserEntity update(Long id, UpdateUserDto updateUserDto);
} 
