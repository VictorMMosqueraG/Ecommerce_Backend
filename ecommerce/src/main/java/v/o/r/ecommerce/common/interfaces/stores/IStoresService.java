package v.o.r.ecommerce.common.interfaces.stores;

import java.util.List;
import java.util.Map;

import v.o.r.ecommerce.stores.dto.CreateStoreDto;
import v.o.r.ecommerce.stores.dto.PaginationStoreDto;
import v.o.r.ecommerce.stores.dto.UpdateStoreDto;
import v.o.r.ecommerce.stores.entities.StoresEntity;

public interface IStoresService {
    public StoresEntity save(CreateStoreDto createStore);
    public List<Map<String,Object>> find(PaginationStoreDto paginationStoreDto);
    public StoresEntity update(Long id, UpdateStoreDto updateStoreDto);
}
