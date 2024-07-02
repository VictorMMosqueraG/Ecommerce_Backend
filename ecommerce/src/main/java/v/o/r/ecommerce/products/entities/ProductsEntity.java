package v.o.r.ecommerce.products.entities;


import v.o.r.ecommerce.categories.entities.CategoryEntity;
import v.o.r.ecommerce.common.enums.EnumUtils;
import v.o.r.ecommerce.common.interfaces.products.IProducts;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "products",uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class ProductsEntity implements IProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToMany
    @JoinTable(name = "products_categories", 
    joinColumns = @JoinColumn(name = "product_id",referencedColumnName = "id"), 
    inverseJoinColumns = @JoinColumn(name = "category_id",referencedColumnName = "id"))
    private List<CategoryEntity> categories;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
    }

    

    
   
    



   
}
