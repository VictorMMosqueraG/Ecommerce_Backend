package v.o.r.ecommerce.stores.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import v.o.r.ecommerce.stores.entities.StoresEntity;

public interface StoreRepository extends JpaRepository<StoresEntity,Long>{
    
}
