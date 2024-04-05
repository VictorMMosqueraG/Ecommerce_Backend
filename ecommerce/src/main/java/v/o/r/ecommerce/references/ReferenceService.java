package v.o.r.ecommerce.references;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import v.o.r.ecommerce.common.interfaces.references.IReferenceService;
import v.o.r.ecommerce.references.dto.createReferenceDTO;
import v.o.r.ecommerce.references.entities.ReferenceEntity;
import v.o.r.ecommerce.references.repositories.ReferenceRepository;
@Validated
@Service
public class ReferenceService implements IReferenceService {
    @Autowired
    private ReferenceRepository referenceRepository;


    public ReferenceEntity save(createReferenceDTO createReference){
        ReferenceEntity entity = new ReferenceEntity();

   
        entity.setName(createReference.name);
        entity.setDescription(createReference.description);
        return referenceRepository.save(entity);
        
    }
    public Optional<ReferenceEntity> findByIdOrFail(Long id){
        Optional<ReferenceEntity> foundReference=id!=null ? this.findById(id):null;
        if (foundReference==null||foundReference.isEmpty()) {
            throw new NoSuchElementException("the Reference: "+id+ "\n is not found.");
        }
        return foundReference;
    }

    public Optional<ReferenceEntity> findById(Long id){
        Optional<ReferenceEntity> foundReference = referenceRepository.findById(id);
        return foundReference;
    }
}
