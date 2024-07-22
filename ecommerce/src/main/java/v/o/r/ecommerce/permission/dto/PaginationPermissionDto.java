package v.o.r.ecommerce.permission.dto;

import io.swagger.v3.oas.annotations.Parameter;

public class PaginationPermissionDto {
    @Parameter(description = "Indicates whether the response should be flattened, example = True")
    private boolean flatten;

    @Parameter(description = "Maximum number of results to return, example = 10")
    private int limit;

    @Parameter(description = "Index of the first result, example = 0")
    private int offset;

    @Parameter(description = "Sort order of the results ,example = asc")
    private String sortOrder;

    @Parameter(description = "find permission by name")
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
