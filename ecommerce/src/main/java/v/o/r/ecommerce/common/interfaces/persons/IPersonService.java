package v.o.r.ecommerce.common.interfaces.persons;

import java.util.List;
import java.util.Map;

import v.o.r.ecommerce.persons.dto.CreatePersonDto;
import v.o.r.ecommerce.persons.dto.PaginationPersonDto;
import v.o.r.ecommerce.persons.entities.PersonEntity;

public interface IPersonService {
  public PersonEntity save(CreatePersonDto createPerson);  
  public List<Map<String,Object>> find(PaginationPersonDto paginationPersonDto);
} 
