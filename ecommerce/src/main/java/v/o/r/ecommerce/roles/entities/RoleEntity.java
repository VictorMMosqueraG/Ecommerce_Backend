package v.o.r.ecommerce.roles.entities;

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
import v.o.r.ecommerce.common.interfaces.roles.IRoles;
import v.o.r.ecommerce.permission.entities.PermissionEntity;

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

    @ManyToMany
    @JoinTable(name = "role_permissions",
    joinColumns = @JoinColumn(name="role_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "permission_id",referencedColumnName = "id"))
    private List<PermissionEntity> permission;

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

    public List<PermissionEntity> getPermission() {
        return permission;
    }

    public void setPermission(List<PermissionEntity> permission) {
        this.permission = permission;
    }
    
}
