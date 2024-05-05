package v.o.r.ecommerce.stores.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class CreateStoreDto {
    @Schema(example = "StoreBig#1")
    public String name;

    @Schema(example = "cr 100 #20-21")
    public String address;

    @Schema(example = "Cali")
    public String city;

    @Schema(example = "valle del cauca")
    public String department;
}
