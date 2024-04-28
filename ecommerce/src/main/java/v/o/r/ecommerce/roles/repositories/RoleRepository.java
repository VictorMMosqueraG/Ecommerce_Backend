package v.o.r.ecommerce.roles.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import v.o.r.ecommerce.roles.entities.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity,Long>{
    
}
