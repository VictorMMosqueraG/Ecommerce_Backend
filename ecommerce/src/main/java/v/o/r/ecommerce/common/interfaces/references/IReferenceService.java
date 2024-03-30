

package v.o.r.ecommerce.common.interfaces.references;

import v.o.r.ecommerce.references.dto.createReferenceDTO;
import v.o.r.ecommerce.references.entities.ReferenceEntity;

public interface IReferenceService {

    
    public ReferenceEntity save(createReferenceDTO createReference);
}
