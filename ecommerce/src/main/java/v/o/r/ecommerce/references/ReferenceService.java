package v.o.r.ecommerce.references;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import v.o.r.ecommerce.common.interfaces.references.IReferenceService;
import v.o.r.ecommerce.references.dto.CreateReferenceDto;
import v.o.r.ecommerce.references.entities.ReferenceEntity;
import v.o.r.ecommerce.references.repositories.ReferenceRepository;

@Validated
@Service
public class ReferenceService implements IReferenceService {
    @Autowired
    private ReferenceRepository referenceRepository;


    public ReferenceEntity save(CreateReferenceDto createReference){
        ReferenceEntity entity = new ReferenceEntity();

   
        entity.setName(createReference.name);
        entity.setDescription(createReference.description);
        return referenceRepository.save(entity);
        
    }
      public Optional<ReferenceEntity> findById(Long id){
        return referenceRepository.findById(id);
    }

    public Optional<ReferenceEntity> findByIdOrFail(Long id){
        Optional<ReferenceEntity> newLink = id!=null ? findById(id):null;

        if(newLink==null || newLink.isEmpty()){
            throw new NoSuchElementException("Reference with id " + id + " not found.");
        }

        return newLink;
    }
}
