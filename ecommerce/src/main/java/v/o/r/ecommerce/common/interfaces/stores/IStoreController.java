package v.o.r.ecommerce.common.interfaces.stores;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;

import v.o.r.ecommerce.stores.dto.CreateStoreDto;
import v.o.r.ecommerce.stores.dto.PaginationStoreDto;

public interface IStoreController {
    public ResponseEntity <?> save (CreateStoreDto createStore);
    
    public ResponseEntity<?> find(
        @ParameterObject 
        @ModelAttribute 
        PaginationStoreDto paginationStoreDto
    );
}
