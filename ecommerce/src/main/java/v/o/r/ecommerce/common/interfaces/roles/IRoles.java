package v.o.r.ecommerce.common.interfaces.roles;

import v.o.r.ecommerce.permission.entities.PermissionEntity;

public interface IRoles {
    public Long getId();
    public void setId(Long id);
    public String getName();
    public void setName(String name);
    public String getDescription();
    public void setDescription(String description);
    public PermissionEntity getPermission();
    public void setPermission(PermissionEntity permission);
}
