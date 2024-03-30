package v.o.r.ecommerce.persons.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import v.o.r.ecommerce.common.interfaces.persons.IPersons;
import v.o.r.ecommerce.users.entities.UserEntity;

@Entity
@Table(name = "person")
public class PersonEntity implements IPersons{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message =  "firstName cannot be blank")
    @NotNull(message = "fistName cannot be null")
    private String firstName;

    @NotBlank(message = "lastName cannot be blank")
    @NotNull(message = "lastName cannot be null")
    private String lastName;

    private String methodOfPay; //COMEBACK this is relation with other table

    private String phoneNumber;

    @NotBlank(message = "address cannot be blank")
    @NotNull(message = "address cannot be null")
    private String address;

    @NotBlank(message = "city cannot be blank")
    @NotNull(message = "city cannot be null")
    private String city;

    @NotBlank(message = "department cannot be blank")
    @NotNull(message = "department cannot be null")
    private String department;

    @OneToOne
    @JoinColumn(name = "users")
    private UserEntity user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMethodOfPay() {
        return methodOfPay;
    }

    public void setMethodOfPay(String methodOfPay) {
        this.methodOfPay = methodOfPay;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    
}
