package v.o.r.ecommerce.common.interfaces.inventories;

import v.o.r.ecommerce.inventories.dto.CreateInventoryDto;
import v.o.r.ecommerce.inventories.entities.InventoryEntity;

public interface IInventoryService {
    public InventoryEntity save(CreateInventoryDto createInventory);
}
