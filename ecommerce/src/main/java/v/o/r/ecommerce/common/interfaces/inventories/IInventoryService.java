package v.o.r.ecommerce.common.interfaces.inventories;

import v.o.r.ecommerce.Inventories.dto.CreateInventory;
import v.o.r.ecommerce.Inventories.entities.InventoryEntity;

public interface IInventoryService {
    public InventoryEntity save(CreateInventory createInventory);
}
