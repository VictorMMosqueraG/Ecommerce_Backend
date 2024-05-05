package v.o.r.ecommerce.permission.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class CreatePermission {
    @Schema(example = "Products.write.all")
    public String name;

    @Schema(example = "permission for registry and update products")
    public String description;    
}
