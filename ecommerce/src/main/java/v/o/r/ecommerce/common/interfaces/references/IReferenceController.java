package v.o.r.ecommerce.common.interfaces.references;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import v.o.r.ecommerce.references.dto.createReferenceDTO;

public interface IReferenceController {
        public ResponseEntity<?> save(@RequestBody createReferenceDTO createReference);
}
