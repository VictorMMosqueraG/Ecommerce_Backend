package v.o.r.ecommerce.references.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class createReferenceDTO {

    @Schema(example = "XL")
    public String name;

    @Schema(example = "xl")
    public String description;
}