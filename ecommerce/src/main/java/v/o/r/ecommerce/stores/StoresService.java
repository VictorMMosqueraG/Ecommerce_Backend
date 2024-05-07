package v.o.r.ecommerce.stores;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import v.o.r.ecommerce.common.interfaces.stores.IStoresService;

import v.o.r.ecommerce.stores.dto.CreateStoreDto;
import v.o.r.ecommerce.stores.entities.StoresEntity;
import v.o.r.ecommerce.stores.repositories.StoreRepository;

@Validated
@Service
public class StoresService implements IStoresService {
    
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


public Optional<StoresEntity> findByIdOrFail(Long id){
    Optional<StoresEntity> foundStore=id!=null ? this.findById(id):null;
    if (foundStore==null || foundStore.isEmpty()) {
        throw new NoSuchElementException("the Store: "+id+ "\n is not found.");
    }
   
    return foundStore;
}

public Optional<StoresEntity> findById(Long id){
    return useStoreRepository.findById(id);
}
}