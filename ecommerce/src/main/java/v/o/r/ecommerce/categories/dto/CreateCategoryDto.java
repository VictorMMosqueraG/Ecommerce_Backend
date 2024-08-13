package v.o.r.ecommerce.categories.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class CreateCategoryDto {

    @Schema(example = "Sport")
    public String name;

    @Schema(example = "sport")
    public String description;
}
