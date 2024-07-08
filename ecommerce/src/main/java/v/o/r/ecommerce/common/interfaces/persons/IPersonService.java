package v.o.r.ecommerce.common.interfaces.persons;

import java.util.List;
import java.util.Map;

import v.o.r.ecommerce.persons.dto.CreatePerson;
import v.o.r.ecommerce.persons.dto.PaginationPersonDto;
import v.o.r.ecommerce.persons.entities.PersonEntity;

public interface IPersonService {
  public PersonEntity save(CreatePerson createPerson);  
  public List<Map<String,Object>> find(PaginationPersonDto paginationPersonDto);
} 
