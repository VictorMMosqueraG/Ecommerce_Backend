package v.o.r.ecommerce.permission.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import v.o.r.ecommerce.common.interfaces.permission.IPermission;

@Entity
@Table(name = "permission",uniqueConstraints={@UniqueConstraint(columnNames={"email"})})
public class PermissionEntity implements IPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name cannot be blank")
    @NotNull(message = "name cannot be null")
    @Column(unique = true, nullable = false)
    private String name;

    @NotBlank(message = "description cannot be blank")
    @NotNull(message = "description cannot be null")
    @Column(nullable = false)
    private String description;

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
}
