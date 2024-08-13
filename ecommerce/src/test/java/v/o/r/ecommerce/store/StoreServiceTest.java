package v.o.r.ecommerce.store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import v.o.r.ecommerce.store.mockData.StoreMockData;
import v.o.r.ecommerce.stores.StoresService;
import v.o.r.ecommerce.stores.dto.CreateStoreDto;
import v.o.r.ecommerce.stores.dto.PaginationStoreDto;
import v.o.r.ecommerce.stores.dto.UpdateStoreDto;
import v.o.r.ecommerce.stores.entities.StoresEntity;
import v.o.r.ecommerce.stores.repositories.StoreRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class StoreServiceTest {

    @Mock
    private StoreRepository storeRepository;

    @InjectMocks
    private StoresService storesService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //NOTE: Save test

    @Test
    public void testSaveStoreSuccessfully(){
        //Initialize variable
        CreateStoreDto createStoreDto = StoreMockData.createStoreDto();
        StoresEntity storesEntity = StoreMockData.createStoresEntity(createStoreDto);

        //Configure method when called
        when(storeRepository.save(
                any(StoresEntity.class)
            )
        ).thenReturn(storesEntity);

        //Call method service
        StoresEntity result = storesService.save(createStoreDto);

        //Asserts
        assertEquals(storesEntity.getName(), result.getName());
        assertEquals(storesEntity.getDepartment(), result.getDepartment());
        assertSame(storesEntity.getCity(), result.getCity());
        assertEquals(storesEntity.getAddress(), result.getAddress());

        //Verify
        verify(storeRepository).save(any(StoresEntity.class));
    }

    //NOTE: Find test

    @Test
    public void testFindStoreSuccessfully(){
        //Initialize variable
        PaginationStoreDto paginationStoreDto = new PaginationStoreDto();
        List<StoresEntity> stores = StoreMockData.listStore();

        //Configure method when called
        when(storeRepository.findAll()).thenReturn(stores);

        //Call method service
        List<Map<String, Object>> result = storesService.find(paginationStoreDto);
         
        Map<String, Object> response = result.get(0);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        //Asserts
        assertEquals("store", response.get("Context"));
        assertEquals(2, data.size());

        //Verify
        verify(storeRepository).findAll();
    }

    @Test
    public void testFindStoreWithFlatten(){//COMEBACK: missing changed in all module test with this test
        //Initialize variable
        List<StoresEntity> stores = StoreMockData.listStoreFlatten();
        PaginationStoreDto paginationStoreDto = new PaginationStoreDto();
        paginationStoreDto.setFlatten(true);

        //Configure method when called
        when(storeRepository.findAll()).thenReturn(stores);

        //Called method service
        List<Map<String, Object>> result = storesService.find(paginationStoreDto);
         
        Map<String, Object> response = result.get(0);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");
        

        //Asserts
        assertEquals("store", response.get("context"));
        assertEquals(2, response.get("total"));
        assertEquals(1L, data.get(0).get("id"));
        assertEquals("name1", data.get(0).get("name"));
        assertEquals(null, data.get(0).get("department"));
        assertEquals(null, data.get(0).get("city"));
        assertEquals(null, data.get(0).get("address"));

        //Verify
        verify(storeRepository).findAll();
    }

    @Test
    public void testFindWithFlattenAndAdditionalFields(){
        //Initialize variable
        PaginationStoreDto paginationStoreDto = new PaginationStoreDto();

        //Configure method when called
        when(storeRepository.findAll()).thenThrow(
            new IllegalArgumentException(
                "The PaginationStoreDto object cannot have other fields besides 'flatten'."
                )
        );

        //Asserts
        assertThrows(IllegalArgumentException.class, ()-> storesService.find(paginationStoreDto));

        //Verify
        verify(storeRepository).findAll();
    }
    

    @Test
    public void testFindWithLimit(){
        //Initialize variable
        List<StoresEntity> stores = StoreMockData.listStore();
        PaginationStoreDto paginationStoreDto = new PaginationStoreDto();
        paginationStoreDto.setLimit(1);

        //Configure method when called
        when(storeRepository.findAll()).thenReturn(stores);

        //Call method service
        List<Map<String, Object>> result = storesService.find(paginationStoreDto);
         
        Map<String, Object> response = result.get(0);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        //Asserts
        assertEquals("store", response.get("Context"));
        assertEquals(2, response.get("total"));
        assertEquals(1, data.size());
        
        //Verify
        verify(storeRepository).findAll();
    }

    @Test
    public void testFindWithOffset(){
        //Initialize variable
        List<StoresEntity> stores = StoreMockData.listStore();
        PaginationStoreDto paginationStoreDto = new PaginationStoreDto();
        paginationStoreDto.setOffset(1);

        //Configure method when called
        when(storeRepository.findAll()).thenReturn(stores);

        //Call method service
        List<Map<String, Object>> result = storesService.find(paginationStoreDto);
         
        Map<String, Object> response = result.get(0);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        //Asserts
        assertEquals("store", response.get("Context"));
        assertEquals(2, response.get("total"));
        assertEquals(1, data.size());
        assertEquals(2L, data.get(0).get("id"));
        assertEquals("name2", data.get(0).get("name"));
        assertEquals("department2", data.get(0).get("department"));
        assertEquals("city2", data.get(0).get("city"));
        assertEquals("address2", data.get(0).get("address"));

        //Verify
        verify(storeRepository).findAll();
    }

    @Test
    public void testFindWithSortOrder(){
        //Initialize variable
        List<StoresEntity> stores = StoreMockData.listStore();
        PaginationStoreDto paginationStoreDto = new PaginationStoreDto();
        paginationStoreDto.setSortOrder("DESC");//COMEBACK: use sortOrderEnum

        //Configure method when called
        when(storeRepository.findAll()).thenReturn(stores);

        //Call method service
        List<Map<String, Object>> result = storesService.find(paginationStoreDto);
         
        Map<String, Object> response = result.get(0);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");


        //Asserts
        assertEquals("store",response.get("Context"));
        assertEquals(2, response.get("total"));
        //data 2
        assertEquals(2L, data.get(0).get("id"));
        assertEquals("name2", data.get(0).get("name"));
        assertEquals("department2", data.get(0).get("department"));
        assertEquals("city2", data.get(0).get("city"));
        assertEquals("address2", data.get(0).get("address"));
        //data 1
        assertEquals(1L, data.get(1).get("id"));
        assertEquals("name1", data.get(0).get("name"));
        assertEquals("department1", data.get(0).get("department"));
        assertEquals("city1", data.get(0).get("city"));
        assertEquals("address1", data.get(0).get("address"));

        //Verify
        verify(storeRepository).findAll();
    }

    @Test
    public void testFindWithCity(){
        //Initialize variable
        List<StoresEntity> stores = StoreMockData.listStore();
        PaginationStoreDto paginationStoreDto = new PaginationStoreDto();
        paginationStoreDto.setCity("city2");

        //Configure method when called
        when(storeRepository.findAll()).thenReturn(stores);

        //Call method service
        List<Map<String, Object>> result = storesService.find(paginationStoreDto);
         
        Map<String, Object> response = result.get(0);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        //Asserts
        assertEquals("store", response.get("Context"));
        assertEquals(2, response.get("total"));
        assertEquals(stores.get(1).getCity(), data.get(0).get("city"));

        //Verify
        verify(storeRepository).findAll();
    }

    @Test
    public void testFindWithDepartment(){
         //Initialize variable
         List<StoresEntity> stores = StoreMockData.listStore();
         PaginationStoreDto paginationStoreDto = new PaginationStoreDto();
         paginationStoreDto.setDepartment("department2");
 
         //Configure method when called
         when(storeRepository.findAll()).thenReturn(stores);
 
         //Call method service
         List<Map<String, Object>> result = storesService.find(paginationStoreDto);
          
         Map<String, Object> response = result.get(0);
         @SuppressWarnings("unchecked")
         List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");
 
         //Asserts
         assertEquals("store", response.get("Context"));
         assertEquals(2, response.get("total"));
         assertEquals(stores.get(1).getDepartment(), data.get(0).get("department"));
 
         //Verify
         verify(storeRepository).findAll();
    }

    //NOTE: Test Update
    @Test
    public void testUpdateSuccessfully(){
        //Initialize variable
        Long id = 1L;
        UpdateStoreDto updateStoreDto = StoreMockData.updateStoreDto();
        StoresEntity storesEntity = StoreMockData.updateStoresEntity(updateStoreDto);

        //Configure method when called
        when(storeRepository.findById(id))
            .thenReturn(Optional.of(storesEntity));

        when(storeRepository.save(
            any(StoresEntity.class))
        ).thenReturn(storesEntity);

        //Call method service
        StoresEntity result = storesService.update(id, updateStoreDto);

        //Asserts
        assertEquals("nameUpdate", result.getName());
        assertEquals("departmentUpdate", result.getDepartment());
        assertEquals("cityUpdate", result.getCity());
        assertEquals("addressUpdate", result.getAddress());

        //Verify
        verify(storeRepository).findById(id);
        verify(storeRepository).save(any(StoresEntity.class));
        
    }

    @Test
    public void testUpdateWithOnlyName(){
        //Initialize variable
        Long id = 1L;
        UpdateStoreDto updateStoreDto = StoreMockData.updateStoreDto();
        updateStoreDto.department=null;
        updateStoreDto.city=null;
        updateStoreDto.address=null;
        StoresEntity storesEntity = StoreMockData.updateStoresEntity(updateStoreDto);

        //Configure method when called
        when(storeRepository.findById(id))
            .thenReturn(Optional.of(storesEntity));

        when(storeRepository.save(
            any(StoresEntity.class))
        ).thenReturn(storesEntity);

        //Call method service
        StoresEntity result = storesService.update(id, updateStoreDto);

        //Asserts
        assertEquals("nameUpdate", result.getName());
        assertEquals(null, result.getDepartment());
        assertEquals(null, result.getCity());
        assertEquals(null, result.getAddress());

        //Verify
        verify(storeRepository).findById(id);
        verify(storeRepository).save(any(StoresEntity.class));
    }

    @Test
    public void testUpdateWithOnlyDepartment(){
        //Initialize variable
        Long id = 1L;
        UpdateStoreDto updateStoreDto = StoreMockData.updateStoreDto();
        updateStoreDto.name=null;
        updateStoreDto.city=null;
        updateStoreDto.address=null;
        StoresEntity storesEntity = StoreMockData.updateStoresEntity(updateStoreDto);

        //Configure method when called
        when(storeRepository.findById(id))
            .thenReturn(Optional.of(storesEntity));

        when(storeRepository.save(
            any(StoresEntity.class))
        ).thenReturn(storesEntity);

        //Call method service
        StoresEntity result = storesService.update(id, updateStoreDto);

        //Asserts
        assertEquals(null, result.getName());
        assertEquals("departmentUpdate", result.getDepartment());
        assertEquals(null, result.getCity());
        assertEquals(null, result.getAddress());

        //Verify
        verify(storeRepository).findById(id);
        verify(storeRepository).save(any(StoresEntity.class));
    }

    @Test
    public void testUpdateWithOnlyCity(){
        //Initialize variable
        Long id = 1L;
        UpdateStoreDto updateStoreDto = StoreMockData.updateStoreDto();
        updateStoreDto.name=null;
        updateStoreDto.department=null;
        updateStoreDto.address=null;
        StoresEntity storesEntity = StoreMockData.updateStoresEntity(updateStoreDto);

        //Configure method when called
        when(storeRepository.findById(id))
            .thenReturn(Optional.of(storesEntity));

        when(storeRepository.save(
            any(StoresEntity.class))
        ).thenReturn(storesEntity);

        //Call method service
        StoresEntity result = storesService.update(id, updateStoreDto);

        //Asserts
        assertEquals(null, result.getName());
        assertEquals(null, result.getDepartment());
        assertEquals("cityUpdate", result.getCity());
        assertEquals(null, result.getAddress());

        //Verify
        verify(storeRepository).findById(id);
        verify(storeRepository).save(any(StoresEntity.class));
    }

    @Test
    public void testUpdateWithOnlyAddress(){
        //Initialize variable
        Long id = 1L;
        UpdateStoreDto updateStoreDto = StoreMockData.updateStoreDto();
        updateStoreDto.name=null;
        updateStoreDto.department=null;
        updateStoreDto.city=null;
        StoresEntity storesEntity = StoreMockData.updateStoresEntity(updateStoreDto);

        //Configure method when called
        when(storeRepository.findById(id))
            .thenReturn(Optional.of(storesEntity));

        when(storeRepository.save(
            any(StoresEntity.class))
        ).thenReturn(storesEntity);

        //Call method service
        StoresEntity result = storesService.update(id, updateStoreDto);

        //Asserts
        assertEquals(null, result.getName());
        assertEquals(null, result.getDepartment());
        assertEquals(null, result.getCity());
        assertEquals("addressUpdate", result.getAddress());

        //Verify
        verify(storeRepository).findById(id);
        verify(storeRepository).save(any(StoresEntity.class));
    }
}
