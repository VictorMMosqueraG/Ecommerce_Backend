package v.o.r.ecommerce.inventory.mockData;

import v.o.r.ecommerce.inventories.dto.CreateInventoryDto;
import v.o.r.ecommerce.inventories.entities.InventoryEntity;
import v.o.r.ecommerce.products.mockData.ProductsMockData;
import v.o.r.ecommerce.references.mockData.ReferenceMockData;
import v.o.r.ecommerce.store.mockData.StoreMockData;

public class InventoryMockData {
    
    //methods for save
    public static CreateInventoryDto createInventoryDto(){
        CreateInventoryDto dto = new CreateInventoryDto();
        dto.idProduct=1L;
        dto.idReference=1L;
        dto.idStore=1L;
        dto.stocks=2;

        return dto;
    }

    public static InventoryEntity createInvInventoryEntity(
        CreateInventoryDto createInventoryDto
    ){
        InventoryEntity inventoryEntity = new InventoryEntity();

        inventoryEntity.setProduct(ProductsMockData.productsEntity());
        inventoryEntity.setReference(ReferenceMockData.referenceEntity());
        inventoryEntity.setStore(StoreMockData.storesEntity());
        inventoryEntity.setStocks(2);

        return inventoryEntity;
    }
}
