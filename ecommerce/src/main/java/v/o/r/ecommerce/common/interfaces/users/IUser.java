package v.o.r.ecommerce.common.interfaces.users;

import java.util.UUID;

public interface IUser {
    UUID getId();
    void setId(UUID id);
    String getEmail();
    void setEmail(String email);
    String getPassword();
    void setPassword(String password);
    String getRole();
    void setRole(String role);
} 
