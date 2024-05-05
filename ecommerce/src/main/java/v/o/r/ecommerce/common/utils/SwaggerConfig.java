package v.o.r.ecommerce.common.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {
    
    @Bean
    public OpenAPI api(){
        return new OpenAPI().info(new Info().title("ECOMMERCE").version("V1"));
    }
}
