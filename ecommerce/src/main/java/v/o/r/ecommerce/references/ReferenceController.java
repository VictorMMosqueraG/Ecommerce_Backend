package v.o.r.ecommerce.references;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import v.o.r.ecommerce.common.service.BaseServiceError;
import v.o.r.ecommerce.references.dto.createReferenceDTO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Validated
@Controller
@RequestMapping("references")
public class ReferenceController {
    
    @Autowired
    ReferenceService useService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody createReferenceDTO createReference) {
       try {
            useService.save(createReference);
           return ResponseEntity.status(HttpStatus.CREATED).build();
        
       } catch (Exception e) {
        return BaseServiceError.handleException(e);
       }
    }
    
}
