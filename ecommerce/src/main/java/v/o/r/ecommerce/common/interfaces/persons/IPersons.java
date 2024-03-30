package v.o.r.ecommerce.common.interfaces.persons;

import v.o.r.ecommerce.users.entities.UserEntity;

public interface IPersons {
    public Long getId();
    public void setId(Long id);
    public String getFirstName();
    public void setFirstName(String firstName);
    public String getLastName();
    public void setLastName(String lastName);
    public String getMethodOfPay();
    public void setMethodOfPay(String methodOfPay);
    public String getPhoneNumber();
    public void setPhoneNumber(String phoneNumber);
    public String getAddress();
    public void setAddress(String address);
    public String getCity();
    public void setCity(String city);
    public String getDepartment();
    public void setDepartment(String department);
    public UserEntity getUser();
    public void setUser(UserEntity user);    
} 
