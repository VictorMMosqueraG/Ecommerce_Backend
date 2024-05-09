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
     public Optional<StoresEntity> findById(Long id){
        return useStoreRepository.findById(id);
    }

    public Optional<StoresEntity> findByIdOrFail(Long id){
        Optional<StoresEntity> newLink = id!=null ? findById(id):null;

        if(newLink==null || newLink.isEmpty()){
            throw new NoSuchElementException("Store with id " + id + " not found.");
        }

        return newLink;
    }

}
