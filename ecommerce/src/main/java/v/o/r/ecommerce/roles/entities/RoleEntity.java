package v.o.r.ecommerce.roles.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import v.o.r.ecommerce.common.interfaces.roles.IRoles;

@Entity
@Table(name = "roles",uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class RoleEntity implements IRoles{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be blank")
    @Column(unique = true, nullable = false)
    private String name;

    @NotBlank(message = "description cannot be blank")
    private String description;

    @NotNull(message = "permission cannot be null")
    @NotBlank(message = "permission cannot be blank")
    @Column(nullable =  false)
    private String permission; //COMEBACK:missing refactor, this value is relation with table permission

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

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    
}
