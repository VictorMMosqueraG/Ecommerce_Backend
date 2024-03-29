package v.o.r.ecommerce.common.interfaces.users;

public interface IUser {
    int getId();
    void setId(int id);
    String getEmail();
    void setEmail(String email);
    String getPassword();
    void setPassword(String password);
    String getRole();
    void setRole(String role);
} 
