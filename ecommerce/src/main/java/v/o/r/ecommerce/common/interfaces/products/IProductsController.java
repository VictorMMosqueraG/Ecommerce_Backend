package v.o.r.ecommerce.common.interfaces.products;

import org.springframework.http.ResponseEntity;

import v.o.r.ecommerce.Products.Dto.ProductsDto;


public interface IProductsController {
public ResponseEntity<?> save(ProductsDto createProduct);  
    
} 