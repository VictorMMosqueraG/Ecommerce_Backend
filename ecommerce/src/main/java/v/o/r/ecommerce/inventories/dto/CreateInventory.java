package v.o.r.ecommerce.inventories.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class CreateInventory {
    
    @Schema(example = "25")
    public Integer stocks;

    @Schema(example = "1")
    public Long idProduct;

    @Schema(example = "3")
    public Long idReference;

    @Schema(example = "8")
    public Long idStore;
}
