package v.o.r.ecommerce.methodOfPay.dto;

public class PaginationMethodOfPayDto {
    private boolean flatten;
    private int limit;
    private int offset;
    private String sortOrder;
    private String name;
    
    public boolean isFlatten() {
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    
}
