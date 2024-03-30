package v.o.r.ecommerce.references;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import v.o.r.ecommerce.references.dto.createReferenceDTO;
import v.o.r.ecommerce.references.entities.ReferenceEntity;
import v.o.r.ecommerce.references.repositories.ReferenceRepository;
@Validated
@Service
public class ReferenceService {
    @Autowired
    private ReferenceRepository useRepository;


    public ReferenceEntity save(createReferenceDTO createReference){
        ReferenceEntity entity = new ReferenceEntity();

   
        entity.setName(createReference.name);
        entity.setDescription(createReference.description);
        return useRepository.save(entity);
        
    }
}
