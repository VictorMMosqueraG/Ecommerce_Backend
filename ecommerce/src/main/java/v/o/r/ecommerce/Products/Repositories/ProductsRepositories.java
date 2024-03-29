package v.o.r.ecommerce.Products.Repositories;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import v.o.r.ecommerce.Products.Entity.ProductsEntity;


public interface ProductsRepositories extends JpaRepository<ProductsEntity,UUID> {
    
}
