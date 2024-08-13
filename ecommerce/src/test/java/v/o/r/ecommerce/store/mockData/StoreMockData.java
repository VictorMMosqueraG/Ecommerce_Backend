package v.o.r.ecommerce.store.mockData;

import v.o.r.ecommerce.stores.dto.CreateStoreDto;
import v.o.r.ecommerce.stores.dto.UpdateStoreDto;
import v.o.r.ecommerce.stores.entities.StoresEntity;

import java.util.List;
import java.util.Arrays;


public class StoreMockData {
    

    //NOTE: method for save
    public static CreateStoreDto createStoreDto(){
        CreateStoreDto dto = new CreateStoreDto();

        dto.name="BigStore";
        dto.department="Department";
        dto.city="city";
        dto.address="street 20";

        return dto;
    }

    public static StoresEntity createStoresEntity(
        CreateStoreDto createStoreDto
    ){
        StoresEntity entity = new StoresEntity();

        entity.setName(createStoreDto.name);
        entity.setDepartment(createStoreDto.department);
        entity.setCity(createStoreDto.city);
        entity.setAddress(createStoreDto.address);

        return entity;
    }

    //NOTE: method for find
    public static List<StoresEntity> listStore(){
        StoresEntity entity1 = new StoresEntity();

        entity1.setId(1L);
        entity1.setName("name1");
        entity1.setDepartment("department1");
        entity1.setCity("city1");
        entity1.setAddress("address1");

        StoresEntity entity2 = new StoresEntity();

        entity2.setId(2L);
        entity2.setName("name2");
        entity2.setDepartment("department2");
        entity2.setCity("city2");
        entity2.setAddress("address2");

        List<StoresEntity> stores = Arrays.asList(entity1,entity2);

        return stores;
    }

    public static List<StoresEntity> listStoreFlatten(){
        StoresEntity entity1 = new StoresEntity();
        entity1.setId(1L);
        entity1.setName("name1");
      
        StoresEntity entity2 = new StoresEntity();
        entity2.setId(2L);
        entity2.setName("name2");

        List<StoresEntity> stores = Arrays.asList(entity1,entity2);

        return stores;
    }

    //NOTE: methods for update

    public static UpdateStoreDto updateStoreDto(){
        UpdateStoreDto dto = new UpdateStoreDto();
        dto.name="nameUpdate";
        dto.department="departmentUpdate";
        dto.city="cityUpdate";
        dto.address="addressUpdate";

        return dto;
    }

    public static StoresEntity updateStoresEntity(
        UpdateStoreDto updateStoreDto
    ){
        StoresEntity entity = new StoresEntity();
        entity.setName(updateStoreDto.name);
        entity.setDepartment(updateStoreDto.department);
        entity.setCity(updateStoreDto.city);
        entity.setAddress(updateStoreDto.address);

        return entity;
    }
}
