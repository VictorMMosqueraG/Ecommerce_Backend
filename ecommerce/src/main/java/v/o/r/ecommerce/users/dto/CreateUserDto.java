package v.o.r.ecommerce.users.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class CreateUserDto {
    @Schema(example = "victormmosquerag@gmail.com")
    public String email;
    
    @Schema(example = "MySecretPassword")
    public String password;
    @Schema(example = "1")
    public Long role;
}
