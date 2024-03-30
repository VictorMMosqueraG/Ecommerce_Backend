package v.o.r.ecommerce.common.interfaces.stores;

import v.o.r.ecommerce.stores.dto.CreateStoreDto;
import v.o.r.ecommerce.stores.entities.StoresEntity;

public interface IStoresService {
    public StoresEntity save(CreateStoreDto createStore);
}
