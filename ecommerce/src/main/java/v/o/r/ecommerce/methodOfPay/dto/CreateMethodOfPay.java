package v.o.r.ecommerce.methodOfPay.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class CreateMethodOfPay {

    @Schema(example = "Cash")
    public String name;

    @Schema(example = "cash")
    public String description;
}
