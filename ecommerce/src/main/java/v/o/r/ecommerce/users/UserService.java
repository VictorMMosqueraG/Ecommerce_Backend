package v.o.r.ecommerce.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import v.o.r.ecommerce.common.interfaces.users.IUserService;
import v.o.r.ecommerce.common.utils.HasMap;
import v.o.r.ecommerce.users.dto.CreateUserDto;
import v.o.r.ecommerce.users.entities.UserEntity;
import v.o.r.ecommerce.users.repositories.UserRepository;


@Service
public class UserService implements IUserService{
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HasMap hasMap;

    public UserEntity save(CreateUserDto createUser){
        UserEntity user = new UserEntity();

        //encode of the password
        if(createUser.password!=null && !createUser.password.isBlank()){
            String encodePassword=hasMap.encodePassword(createUser.password);
            user.setPassword(encodePassword);
        }
        user.setPassword(createUser.password);
        user.setEmail(createUser.email);
        user.setRole(createUser.role);

        return userRepository.save(user);
    }
}
