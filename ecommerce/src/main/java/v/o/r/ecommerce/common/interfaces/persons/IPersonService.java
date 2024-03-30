package v.o.r.ecommerce.common.interfaces.persons;

import v.o.r.ecommerce.persons.dto.CreatePerson;
import v.o.r.ecommerce.persons.entities.PersonEntity;

public interface IPersonService {
  public PersonEntity save(CreatePerson createPerson);  
} 
