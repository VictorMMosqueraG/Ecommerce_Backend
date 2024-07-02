package v.o.r.ecommerce.common.interfaces.roles;

import v.o.r.ecommerce.common.enums.sortOrderEnum;

public interface IPaginationRole{ 
    public boolean getFlatten();
    public void setFlatten(boolean flatten);
    public int getLimit();
    public void setLimit(int limit);
    public int getOffset();
    public void setOffset(int offset);
    public sortOrderEnum getSortOrder();
    public void setSortOrder(sortOrderEnum sortOrder); 
    public String getName();
    public void setName(String name); 
    public String getPermission() ;
    public void setPermission(String permission); 
} 
