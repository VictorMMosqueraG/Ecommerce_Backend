package v.o.r.ecommerce.common.interfaces.stores;

public interface IPaginationStore {
    public boolean isFlatten();
    public void setFlatten(boolean flatten);
    public int getLimit();
    public void setLimit(int limit);
    public int getOffset();
    public void setOffset(int offset);
    public String getSortOrder();
    public void setSortOrder(String sortOrder);
    public String getCity();
    public void setCity(String city);
    public String getDepartment();
    public void setDepartment(String department);
}
