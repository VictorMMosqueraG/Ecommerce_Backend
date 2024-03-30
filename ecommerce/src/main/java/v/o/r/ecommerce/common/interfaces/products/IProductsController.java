package v.o.r.ecommerce.common.interfaces.products;

import org.springframework.http.ResponseEntity;

import v.o.r.ecommerce.products.dto.ProductsDto;


public interface IProductsController {
public ResponseEntity<?> save(ProductsDto createProduct);  
    
} 