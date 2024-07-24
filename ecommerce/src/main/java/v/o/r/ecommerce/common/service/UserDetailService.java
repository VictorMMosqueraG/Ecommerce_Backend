package v.o.r.ecommerce.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import v.o.r.ecommerce.roles.entities.RoleEntity;
import v.o.r.ecommerce.users.entities.UserEntity;
import v.o.r.ecommerce.users.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

//NOTE: this class find user and return instance userDetail or return error
@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    //NOTE: this methods is for valid if user exist and extract permission
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> foundUser = userRepository.findByEmail(username);

        if (!foundUser.isPresent()) {
            throw new UsernameNotFoundException("User not found.");//COMEBACK test this
        }

        UserEntity user = foundUser.get();
        return new User(
            user.getEmail(),
            user.getPassword(),
            true,
            true,
            true,
            true,
            getAuthorities(user.getRole())
        );
    }

    //NOTE: Find in role and extract permission 
    private Collection<? extends GrantedAuthority> getAuthorities(RoleEntity role) {
        return role.getPermission().stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getName()))
            .collect(Collectors.toList());
    }
}
