package v.o.r.ecommerce.categories.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import v.o.r.ecommerce.categories.entities.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Long>{

    
}
