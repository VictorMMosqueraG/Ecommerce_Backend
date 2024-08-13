package v.o.r.ecommerce.common.interfaces.references;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import v.o.r.ecommerce.references.dto.CreateReferenceDto;

public interface IReferenceController {
        public ResponseEntity<?> save(@RequestBody CreateReferenceDto createReference);
}
