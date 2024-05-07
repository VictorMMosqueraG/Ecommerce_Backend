package v.o.r.ecommerce.products.dto;


public class PaginationProductDto {
    private boolean flatten;
    private int limit;
    private int offset;
    private String sortOrder;
    private String name;
    private String price;
    private String nameCategories;
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
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getNameCategories() {
        return nameCategories;
    }
    public void setNameCategories(String nameCategories) {
        this.nameCategories = nameCategories;
    }
    

 }
