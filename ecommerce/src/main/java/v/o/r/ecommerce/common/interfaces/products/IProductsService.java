package v.o.r.ecommerce.common.interfaces.products;



import java.util.List;
import java.util.Map;

import v.o.r.ecommerce.products.dto.PaginationProductDto;
import v.o.r.ecommerce.products.dto.CreateProductDto;
import v.o.r.ecommerce.products.entities.ProductsEntity;

public interface IProductsService {
  public ProductsEntity save(CreateProductDto createProducts);  
  public List<Map<String, Object>> find(PaginationProductDto paginationProductDto);
} 