package v.o.r.ecommerce.common.interfaces.inventories;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import v.o.r.ecommerce.inventories.dto.CreateInventoryDto;

public interface IInventoryController {
    public ResponseEntity<?> save(@RequestBody CreateInventoryDto createInventory);
}
