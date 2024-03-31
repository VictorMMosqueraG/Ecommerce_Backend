package v.o.r.ecommerce.common.interfaces.stores;

import org.springframework.http.ResponseEntity;

import v.o.r.ecommerce.stores.dto.CreateStoreDto;

public interface IStoreController {
    

    public ResponseEntity <?> save (CreateStoreDto createStore);
}
