package v.o.r.ecommerce.persons.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class CreatePerson {
    @Schema(example = "Victor")
    public String firstName;

    @Schema(example = "Mosquera")
    public String lastName;

    @Schema(example = "Cash")
    public String methodOfPay;

    @Schema(example = "32077751512")
    public String phoneNumber; 

    @Schema(example = "cr 20 #20-40")
    public String address;

    @Schema(example = "cali")
    public String city;

    @Schema(example = "valle del cauca")
    public String department;

    @Schema(example = "1")
    public Long user;
}
