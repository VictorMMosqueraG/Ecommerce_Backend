package v.o.r.ecommerce.categories.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class CategoryDto {

    @Schema(example = "Sport")
    public String name;

    @Schema(example = "sport")
    public String description;
}
