package v.o.r.ecommerce.inventories.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import v.o.r.ecommerce.inventories.entities.InventoryEntity;

public interface InventoryRepository extends JpaRepository<InventoryEntity,Long>{
    
}
