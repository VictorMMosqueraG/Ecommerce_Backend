package v.o.r.ecommerce.inventories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import v.o.r.ecommerce.common.interfaces.inventories.IInventoryService;
import v.o.r.ecommerce.inventories.dto.CreateInventory;
import v.o.r.ecommerce.inventories.entities.InventoryEntity;
import v.o.r.ecommerce.inventories.repositories.InventoryRepository;
import v.o.r.ecommerce.products.ProductsService;
import v.o.r.ecommerce.references.ReferenceService;
import v.o.r.ecommerce.stores.StoresService;

@Validated
@Service
public class InventoriesService implements IInventoryService{
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired 
    private ProductsService productsService;

    @Autowired
    private ReferenceService referenceService;

    @Autowired
    private StoresService storesService;

    public InventoryEntity save(CreateInventory createInventory){
        InventoryEntity inventory = new InventoryEntity();
        inventory.setStocks(createInventory.stocks);
        
        inventory.setProduct(productsService.findByIdOrFail(createInventory.idProduct).get());
        inventory.setStore(storesService.findByIdOrFail(createInventory.idStore).get());
        inventory.setReference(referenceService.findByIdOrFail(createInventory.idStore).get());
        
        
        return inventoryRepository.save(inventory);
    }
}
