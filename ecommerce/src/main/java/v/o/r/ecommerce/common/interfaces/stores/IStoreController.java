package v.o.r.ecommerce.common.interfaces.stores;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import v.o.r.ecommerce.stores.dto.CreateStoreDto;
import v.o.r.ecommerce.stores.dto.PaginationStoreDto;
import v.o.r.ecommerce.stores.dto.UpdateStoreDto;

public interface IStoreController {
    public ResponseEntity <?> save (CreateStoreDto createStore);
    
    public ResponseEntity<?> find(
        @ParameterObject 
        @ModelAttribute 
        PaginationStoreDto paginationStoreDto
    );

     public ResponseEntity<?> update(
        @PathVariable Long id,
        @RequestBody UpdateStoreDto updateStoreDto
    );
}
