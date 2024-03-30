package v.o.r.ecommerce.common.interfaces.products;



import v.o.r.ecommerce.products.dto.ProductsDto;
import v.o.r.ecommerce.products.entities.ProductsEntity;

public interface IProductsService {
  public ProductsEntity save(ProductsDto createProducts);  
} 