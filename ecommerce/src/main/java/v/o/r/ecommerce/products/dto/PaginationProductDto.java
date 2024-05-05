package v.o.r.ecommerce.products.dto;

import io.swagger.v3.oas.annotations.Parameter;

public class PaginationProductDto {

    @Parameter(description = "Indicates whether the response should be flattened, example = True")
    private boolean flatten;

    @Parameter(description = "Maximum number of results to return, example = 10")
    private int limit;

    @Parameter(description = "Index of the first result, example = 0")
    private int offset;

    @Parameter(description = "Sort order of the result, example = ASC")
    private String sortOrder;

    @Parameter(description = "Filter by name products, example = Short")
    private String name;

    @Parameter(description = "Filter by price, example = 100,000")
    private String price;

    @Parameter(description = "Filter by category, example = Sport")
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
