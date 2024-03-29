package v.o.r.ecommerce.common.interfaces.products;



import v.o.r.ecommerce.Products.Dto.ProductsDto;
import v.o.r.ecommerce.Products.Entity.ProductsEntity;

public interface IProductsService {
  public ProductsEntity save(ProductsDto createProducts);  
} 