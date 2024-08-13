package v.o.r.ecommerce.common.interfaces.products;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;

import v.o.r.ecommerce.products.dto.PaginationProductDto;
import v.o.r.ecommerce.products.dto.CreateProductDto;


public interface IProductsController {
public ResponseEntity<?> save(CreateProductDto createProduct);  
public ResponseEntity<?> find(@ModelAttribute PaginationProductDto paginationProductDto);
} 