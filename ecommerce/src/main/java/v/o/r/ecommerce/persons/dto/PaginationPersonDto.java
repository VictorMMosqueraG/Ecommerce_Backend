package v.o.r.ecommerce.persons.dto;

public class PaginationPersonDto {
    private boolean flatten;
    private int limit;
    private int offset;
    private String sortOrder;
    private String firstName;
    private String lastName;
    
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
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }    
}
