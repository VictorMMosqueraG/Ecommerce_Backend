package v.o.r.ecommerce.stores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import v.o.r.ecommerce.stores.dto.CreateStoreDto;
import v.o.r.ecommerce.stores.entities.StoresEntity;
import v.o.r.ecommerce.stores.repositories.StoreRepository;

@Validated
@Service
public class StoresService {
    
    @Autowired
    private StoreRepository useStoreRepository;

    public StoresEntity save(CreateStoreDto createStore){
       StoresEntity store = new StoresEntity();

       store.setName(createStore.name);
       store.setAddress(createStore.address);
       store.setCity(createStore.city);
       store.setDepartment(createStore.department);
       
       
        return useStoreRepository.save(store);
        
    }

}
