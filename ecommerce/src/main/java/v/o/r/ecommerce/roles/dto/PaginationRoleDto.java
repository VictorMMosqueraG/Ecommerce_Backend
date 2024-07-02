package v.o.r.ecommerce.roles.dto;


import io.swagger.v3.oas.annotations.Parameter;
import v.o.r.ecommerce.common.enums.sortOrderEnum;
import v.o.r.ecommerce.common.interfaces.roles.IPaginationRole;

public class PaginationRoleDto implements IPaginationRole{
    
    @Parameter(description = "Indicates whether the response should be flattened, example = True")
    private boolean flatten;

    @Parameter(description = "Maximum number of results to return, example = 10")
    private int limit;

    @Parameter(description = "Index of the first result, example = 0")
    private int offset;

    @Parameter(description = "Sort order of the results ,example = asc")
    private sortOrderEnum sortOrder;

    @Parameter(description = "name of role")
    private String name;

    @Parameter(description = "permission from role")
    private String permission;

    public boolean getFlatten() {return flatten;}
    public void setFlatten(boolean flatten) {this.flatten = flatten;}
    public int getLimit() {return limit;}
    public void setLimit(int limit) {this.limit = limit;}
    public int getOffset() {return offset;}
    public void setOffset(int offset) {this.offset = offset;}
    public sortOrderEnum getSortOrder() {return sortOrder;}
    public void setSortOrder(sortOrderEnum sortOrder) {this.sortOrder = sortOrder;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getPermission() {return permission;}
    public void setPermission(String permission) {this.permission = permission;}
}
