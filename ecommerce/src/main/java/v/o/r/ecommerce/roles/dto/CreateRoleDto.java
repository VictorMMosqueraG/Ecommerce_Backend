package v.o.r.ecommerce.roles.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public class CreateRoleDto {

    @Schema(example = "Super User")
    public  String name;

    @Schema(example = "Super User with all access")
    public String description;

    @Schema(example = "[1,2,3]")
    public List<Long> permission; 
}
