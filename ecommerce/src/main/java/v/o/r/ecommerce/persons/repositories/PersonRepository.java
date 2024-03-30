package v.o.r.ecommerce.persons.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import v.o.r.ecommerce.persons.entities.PersonEntity;

public interface PersonRepository extends JpaRepository<PersonEntity,Long>{

    
} 
