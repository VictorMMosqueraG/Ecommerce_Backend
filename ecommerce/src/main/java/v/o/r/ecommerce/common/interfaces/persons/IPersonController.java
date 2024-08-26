package v.o.r.ecommerce.common.interfaces.persons;

import org.springframework.http.ResponseEntity;

import v.o.r.ecommerce.persons.dto.CreatePersonDto;
import v.o.r.ecommerce.persons.dto.PaginationPersonDto;

public interface IPersonController {
    public ResponseEntity<?> save(CreatePersonDto createPerson);
    public ResponseEntity<?> find(PaginationPersonDto paginationPersonDto);
}
