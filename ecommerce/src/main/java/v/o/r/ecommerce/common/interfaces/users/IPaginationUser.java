package v.o.r.ecommerce.common.interfaces.users;

public interface IPaginationUser {
    public boolean getFlatten();

    public void setFlatten(boolean flatten);

    public int getLimit();

    public void setLimit(int limit);

    public int getOffset();

    public void setOffset(int offset);

    public String getSortOrder();

    public void setSortOrder(String sortOrder);

    public String getEmail();

    public void setEmail(String name);

    public String getRole();

    public void setRole(String role);
}
