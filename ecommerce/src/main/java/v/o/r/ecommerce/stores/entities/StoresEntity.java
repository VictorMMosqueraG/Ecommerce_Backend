package v.o.r.ecommerce.stores.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "stores", uniqueConstraints = {
            @UniqueConstraint(columnNames = { "name", "address" , "city", "department"})
})
public class StoresEntity {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "name cannot be empty")
    @NotNull(message = "name cannot be null")
    @Column(nullable = false)
    String name;

    @NotBlank(message = "address cannot be empty")
    @NotNull(message = "address cannot be null")
    @Column(nullable = false)
    String address;

    @NotBlank(message = "city cannot be empty")
    @NotNull(message = "city cannot be null")
    @Column(nullable = false)
    String city;

    @NotBlank(message = "department cannot be empty")
    @NotNull(message = "department cannot be null")
    @Column(nullable = false)
    String department;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    

}
