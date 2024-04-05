package v.o.r.ecommerce.Inventories.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import v.o.r.ecommerce.Inventories.entities.InventoryEntity;

public interface InventoryRepository extends JpaRepository<InventoryEntity,Long>{
    
}
