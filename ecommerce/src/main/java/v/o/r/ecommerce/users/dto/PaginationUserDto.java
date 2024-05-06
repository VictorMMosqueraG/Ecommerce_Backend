package v.o.r.ecommerce.users.dto;


import io.swagger.v3.oas.annotations.Parameter;
import v.o.r.ecommerce.common.interfaces.users.IPaginationUser;

public class PaginationUserDto implements IPaginationUser{

    @Parameter(description = "Indicates whether the response should be flattened, example = True")
    private boolean flatten;

    @Parameter(description = "Maximum number of results to return, example = 10")
    private int limit;

    @Parameter(description = "Index of the first result, example = 0")
    private int offset;

    @Parameter(description = "Sort order of the results ,example = asc")
    private String sortOrder;

    @Parameter(description = "Filter users by email address, example = email@gmail.com")
    private String email;

    @Parameter(description = "Filter users by role, example = Super User")
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
