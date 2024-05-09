package v.o.r.ecommerce.common.interfaces.inventories;

import v.o.r.ecommerce.inventories.dto.CreateInventory;
import v.o.r.ecommerce.inventories.entities.InventoryEntity;

public interface IInventoryService {
    public InventoryEntity save(CreateInventory createInventory);
}
