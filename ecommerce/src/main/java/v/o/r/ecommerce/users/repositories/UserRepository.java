package v.o.r.ecommerce.users.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import v.o.r.ecommerce.users.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long>{

    Optional<UserEntity> findByEmail(String userName);
}
