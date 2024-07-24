package v.o.r.ecommerce.products;

import java.util.List;
import java.util.Map;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import v.o.r.ecommerce.common.interfaces.products.IProductsController;
import v.o.r.ecommerce.common.service.BaseServiceError;
import v.o.r.ecommerce.products.dto.PaginationProductDto;
import v.o.r.ecommerce.products.dto.ProductsDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;


@Validated
@RestController
@Tag(name="products")
@RequestMapping("products")
public class ProductsController implements IProductsController{
    
    @Autowired
    private ProductsService productsService;

    
    @Operation(summary = "Save a products")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201", 
            description = "Product created",
            content = @Content(mediaType = "application/json")    
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "Bad request",
            content = @Content(mediaType = "application/json", 
                schema = @Schema(example = "{\"code\": \"BAD_REQUEST\", \"error\": \"Bad request\", \"message\": \"Invalid input data\" }"))
        ),
        @ApiResponse(
            responseCode = "500", 
            description = "Internal Server Error",
            content = @Content(mediaType = "application/json", 
            schema = @Schema(example = "{\"code\": \"UNEXPECTED_ERROR\", \"error\": \"Internal Server Error\", \"message\": \"Unexpected Error\" }"))    
        )
    })
    @PreAuthorize("hasAnyAuthority('Product.write.all','Product.write')")
    @PostMapping
    public ResponseEntity<?> save(@RequestBody ProductsDto createProduct){
        try {
            productsService.save(createProduct);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return BaseServiceError.handleException(e);
        } 
    }

    @Operation(summary = "Found a product")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Found product", 
            content = @Content(mediaType = "application/json",
                schema = @Schema(example = "[\n{\n  \"context\": \"products\",\n  \"total\": 0,\n  \"data\": []\n}\n]"))
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "Bad request",
            content = @Content(mediaType = "application/json", 
                schema = @Schema(example = "{\"code\": \"BAD_REQUEST\", \"error\": \"Bad request\", \"message\": \"Invalid input data\" }"))
        ),
        @ApiResponse(
            responseCode = "500", 
            description = "Internal Server Error",
            content = @Content(mediaType = "application/json", 
                schema = @Schema(example = "{\"code\": \"UNEXPECTED_ERROR\", \"error\": \"Internal Server Error\", \"message\": \"Unexpected Error\" }"))
        )
    })
    @PreAuthorize("hasAuthority('Product.read')")
    @GetMapping
    public ResponseEntity<?> find(@ParameterObject @ModelAttribute PaginationProductDto paginationProductDto){
        try {
            List<Map<String,Object>> foundProduct = productsService.find(paginationProductDto);
            return ResponseEntity.status(HttpStatus.OK).body(foundProduct);
        } catch (Exception e) {
            return BaseServiceError.handleException(e);
        }
        
    }

}
