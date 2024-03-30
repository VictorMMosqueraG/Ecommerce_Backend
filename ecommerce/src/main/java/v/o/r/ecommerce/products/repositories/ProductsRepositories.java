package v.o.r.ecommerce.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import v.o.r.ecommerce.products.entities.ProductsEntity;


public interface ProductsRepositories extends JpaRepository<ProductsEntity,Long> {
    
}
