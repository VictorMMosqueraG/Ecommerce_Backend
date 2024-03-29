package v.o.r.ecommerce.Products.Entity;

import java.util.UUID;

import v.o.r.ecommerce.common.utils.EnumUtils;

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
@Table(name = "products",uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class ProductsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull(message = "name product can not be null")
    @NotBlank(message="name product can not be empty")
    @Column(nullable = false,unique = true)
    private String name;

    @NotNull(message = "description product can not be null")
    @NotBlank(message="description product can not be empty")
    @Column(nullable = false)
    private String description;


    @NotNull(message = "price product can not be null")
    @NotBlank(message="price product can not be empty")
    @Column(nullable = false)
    private String price;
    
 
    private EnumUtils money;

    @NotNull(message = "categories product can not be null")
    @NotBlank(message="categories product can not be empty")
    @Column(nullable = false)
    private String categories;



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

  

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getPrice() {
        return price;
    }

    
    public void setPrice(String price) {

        this.price = price;
    }

    public EnumUtils getMoney() {
        return money;
    }

    public void setMoney(EnumUtils money) {
        this.money = money;
    }

    

    
   
    



   
}
