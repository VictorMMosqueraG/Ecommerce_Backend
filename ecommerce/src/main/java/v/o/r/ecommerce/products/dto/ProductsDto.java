package v.o.r.ecommerce.products.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import v.o.r.ecommerce.common.enums.EnumUtils;

public class ProductsDto {

    @Schema(example = "Shorts")
    public String name;

    @Schema(example = "Short for men")
    public String description;

    @Schema(example = "200.000")
    public String price;

    @Schema(example = "1,2,3")
    public List<Long> categories;

    @Schema(example = "COP")
    public EnumUtils money;
    
}
