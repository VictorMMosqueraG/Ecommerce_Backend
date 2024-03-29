package v.o.r.ecommerce.users.dto;

import v.o.r.ecommerce.common.interfaces.users.IPaginationUser;

public class PaginationUserDto implements IPaginationUser{
    private boolean flatten;
    private int limit;
    private int offset;
    private String sortOrder;
    private String email;
    private String role;

    public boolean getFlatten() {
        return flatten;
    }

    public void setFlatten(boolean flatten) {
        this.flatten = flatten;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    
}
