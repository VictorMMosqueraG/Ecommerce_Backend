package v.o.r.ecommerce.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import v.o.r.ecommerce.common.interfaces.products.IProductsController;
import v.o.r.ecommerce.common.service.BaseServiceError;
import v.o.r.ecommerce.products.dto.ProductsDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
@RestController
@Tag(name="products")
@RequestMapping("products")
public class ProductsController implements IProductsController{
    
    @Autowired
    private ProductsService productsService;

    
    @Operation(summary = "Save a products")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Product created"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ProductsDto createProduct){
        try {
            productsService.save(createProduct);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return BaseServiceError.handleException(e);
        } 
    }

}
