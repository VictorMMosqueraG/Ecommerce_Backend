package v.o.r.ecommerce.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import v.o.r.ecommerce.users.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long>{
    
}
