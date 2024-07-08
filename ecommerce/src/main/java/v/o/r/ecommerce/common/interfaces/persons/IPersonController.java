package v.o.r.ecommerce.common.interfaces.persons;

import org.springframework.http.ResponseEntity;

import v.o.r.ecommerce.persons.dto.CreatePerson;
import v.o.r.ecommerce.persons.dto.PaginationPersonDto;

public interface IPersonController {
    public ResponseEntity<?> save(CreatePerson createPerson);
    public ResponseEntity<?> find(PaginationPersonDto paginationPersonDto);
}
