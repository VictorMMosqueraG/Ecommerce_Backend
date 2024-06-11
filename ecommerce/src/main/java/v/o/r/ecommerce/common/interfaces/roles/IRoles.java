package v.o.r.ecommerce.common.interfaces.roles;

import java.util.List;

import v.o.r.ecommerce.permission.entities.PermissionEntity;

public interface IRoles {
    public Long getId();
    public void setId(Long id);
    public String getName();
    public void setName(String name);
    public String getDescription();
    public void setDescription(String description);
    public List<PermissionEntity> getPermission();
    public void setPermission(List<PermissionEntity> permission);
}
