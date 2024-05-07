package v.o.r.ecommerce.common.interfaces.products;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;

import v.o.r.ecommerce.products.dto.PaginationProductDto;
import v.o.r.ecommerce.products.dto.ProductsDto;


public interface IProductsController {
public ResponseEntity<?> save(ProductsDto createProduct);  
public ResponseEntity<?> find(@ModelAttribute PaginationProductDto paginationProductDto);
} 