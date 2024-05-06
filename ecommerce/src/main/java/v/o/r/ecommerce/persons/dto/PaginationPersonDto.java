package v.o.r.ecommerce.persons.dto;

import io.swagger.v3.oas.annotations.Parameter;

public class PaginationPersonDto {

    @Parameter(description = "Indicate where the response should be flattened, example = true")
    private boolean flatten;

    @Parameter(description = "Maximum number or result to return, example = 10")
    private int limit;

    @Parameter(description = "Index of the first result, example = 0")
    private int offset;

    @Parameter(description = "Sort order of the result, example = ASC")
    private String sortOrder;

    @Parameter(description = "Filter by firstName, example = Victor")
    private String firstName;

    @Parameter(description = "Filter by lastName, example = Mosquera")
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
