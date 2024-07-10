package v.o.r.ecommerce.stores;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import v.o.r.ecommerce.common.interfaces.stores.IStoresService;
import v.o.r.ecommerce.stores.dto.CreateStoreDto;
import v.o.r.ecommerce.stores.dto.PaginationStoreDto;
import v.o.r.ecommerce.stores.entities.StoresEntity;
import v.o.r.ecommerce.stores.repositories.StoreRepository;
import java.util.function.Predicate;
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

    public List<Map<String, Object>> find(PaginationStoreDto paginationStoreDto) {

        //find all data in the database
        List<StoresEntity> foundStore = useStoreRepository.findAll();

        //Destructuring all pagination
        boolean flatten = paginationStoreDto!= null && paginationStoreDto.isFlatten();
        int limit = paginationStoreDto!=null && paginationStoreDto.getLimit()!=0 ? paginationStoreDto.getLimit():50;
        int offset = paginationStoreDto!=null && paginationStoreDto.getOffset()!=0 ? paginationStoreDto.getOffset():0;
        String sortOrder = paginationStoreDto!=null ? paginationStoreDto.getSortOrder():null;
        String city = paginationStoreDto!=null ? paginationStoreDto.getCity():null;
        String department = paginationStoreDto!=null ? paginationStoreDto.getDepartment():null; 


        if(flatten==true){
            if (flatten ==true && ( limit !=50  || offset != 0 || city != null || department != null || sortOrder!=null)) {
                throw new IllegalArgumentException("The PaginationStoreDto object cannot have other fields besides 'flatten'.");
            }

            Map<String,Object> response = new LinkedHashMap<>();
            response.put("context","store");
            response.put("total", foundStore.size());
            response.put("data", foundStore.stream()
                .map(store ->{
                    Map<String,Object> mapStore = new LinkedHashMap<>();
                    mapStore.put("id", store.getId());
                    mapStore.put("name", store.getName());
                    return mapStore;
                })
                .collect(Collectors.toList())
            );
                return Collections.singletonList(response);
        }
        
         //logic for sortOrder DESC, this is default ASC
        Comparator<Map<String, Object>> sort = Comparator.comparing(m -> (String) m.get("email"));

        if (sortOrder != null && sortOrder.equalsIgnoreCase("desc")) {
            sort = sort.reversed();
        }

        //Filter based on city and department if provided
        Predicate<StoresEntity> cityFilter = 
            store -> city == null || store.getCity()
                .toLowerCase()
                .contains(city.toLowerCase()); 

        Predicate<StoresEntity> departmentFilter = 
            store -> department == null || store.getDepartment()
                .toLowerCase()
                .contains(department.toLowerCase());

        Map<String,Object> response = new LinkedHashMap<>();
        response.put("Context", "store");
        response.put("total", foundStore.size());
        response.put("data", foundStore.stream()
            .filter(departmentFilter.and(cityFilter))
            .map(store->{
                Map<String,Object> mapStore = new LinkedHashMap<>();
                mapStore.put("id", store.getId());
                mapStore.put("name", store.getName());
                mapStore.put("city", store.getCity());
                mapStore.put("address", store.getAddress());
                mapStore.put("department", store.getDepartment());

                return mapStore;
            })
            .sorted(sort)
            .skip(offset)
            .limit(limit)
            .collect(Collectors.toList())
        );

        List<Map<String,Object>> result = new LinkedList<>();
        result.add(response);
        return result;
    }

    //NOTE: base methods
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
