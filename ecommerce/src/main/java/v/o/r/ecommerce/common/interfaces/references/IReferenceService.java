

package v.o.r.ecommerce.common.interfaces.references;

import v.o.r.ecommerce.references.dto.CreateReferenceDto;
import v.o.r.ecommerce.references.entities.ReferenceEntity;

public interface IReferenceService {

    
    public ReferenceEntity save(CreateReferenceDto createReference);
}
