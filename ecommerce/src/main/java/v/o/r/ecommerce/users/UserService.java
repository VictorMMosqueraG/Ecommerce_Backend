package v.o.r.ecommerce.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import v.o.r.ecommerce.common.interfaces.users.IUserService;
import v.o.r.ecommerce.common.utils.HasMap;
import v.o.r.ecommerce.users.dto.CreateUserDto;
import v.o.r.ecommerce.users.dto.PaginationUserDto;
import v.o.r.ecommerce.users.entities.UserEntity;
import v.o.r.ecommerce.users.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
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
        }else{
            user.setPassword(createUser.password);
        }

        user.setEmail(createUser.email);
        user.setRole(createUser.role);
      
        return userRepository.save(user);
    }

    public List<Map<String, Object>> find(PaginationUserDto paginationUserDto ){
        List<UserEntity> foundUser = userRepository.findAll();

        //Destructuring paginationUserDto
        boolean flatten = paginationUserDto != null && paginationUserDto.getFlatten();
        int limit = paginationUserDto!=null && paginationUserDto.getLimit() != 0 ? paginationUserDto.getLimit() : 50;
        int offset = paginationUserDto!=null && paginationUserDto.getOffset() != 0 ? paginationUserDto.getOffset() : 0;
        String sortOrder = paginationUserDto!=null ? paginationUserDto.getSortOrder() : null;
        String email = paginationUserDto!=null ? paginationUserDto.getEmail() : null;
        String role = paginationUserDto!=null ? paginationUserDto.getRole():null;

        //validation if provided flatten
        if(flatten==true){
            if (flatten ==true && ( limit !=50  || offset != 0 || email != null || role != null || sortOrder!=null)) {
                throw new IllegalArgumentException("The PaginationUserDto object cannot have other fields besides 'flatten'.");
            }

            Map<String,Object> response = new LinkedHashMap<>();
            response.put("context", "user");
            response.put("total", foundUser.size());
            response.put("date",foundUser.stream()
                .map(user ->{
                    Map<String, Object> userMap = new LinkedHashMap<>();
                    userMap.put("id", user.getId());
                    userMap.put("email", user.getEmail());
                    return userMap;
                })
                .collect(Collectors.toList()));
                return Collections.singletonList(response);
        }

        //logic for sortOrder DESC, this is default ASC
        Comparator<Map<String, Object>> sort = Comparator.comparing(m -> (String) m.get("email"));

        if (sortOrder != null && sortOrder.equalsIgnoreCase("desc")) {
            sort = sort.reversed();
        }

        //filter based on email or role if provided
        Predicate<UserEntity> emailFilter = user -> email == null || user.getEmail().toLowerCase().contains(email.toLowerCase());
        Predicate<UserEntity> roleFilter = user -> role == null || user.getRole().toLowerCase().contains(role.toLowerCase());
        
        //iterates from foundUser and extract id,email and roles
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("context", "user");
        response.put("total", foundUser.size());
        response.put("data", foundUser.stream()
            .filter(emailFilter.and(roleFilter))
            .map(user -> {
                Map<String, Object> userMap = new LinkedHashMap<>(); 
                userMap.put("id", user.getId());
                userMap.put("email", user.getEmail());
                userMap.put("role", user.getRole());
                return userMap;
            })
            .sorted(sort)
            .skip(offset)
            .limit(limit)
            .collect(Collectors.toList()));

        List<Map<String, Object>> resultList = new ArrayList<>();
        resultList.add(response);
        return resultList;        
    }

    public Optional<UserEntity> findById(Long id){
        Optional<UserEntity> foundUser = id!=null ?  userRepository.findById(id) : null;
        
        if(foundUser==null || foundUser.isEmpty()){
            throw new NoSuchElementException("User with id " + id + " not found.");
        }
   
        return foundUser;
    }
}
