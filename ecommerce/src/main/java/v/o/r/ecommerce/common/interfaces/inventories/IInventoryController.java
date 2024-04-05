package v.o.r.ecommerce.common.interfaces.inventories;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import v.o.r.ecommerce.Inventories.dto.CreateInventory;

public interface IInventoryController {
    public ResponseEntity<?> save(@RequestBody CreateInventory createInventory);
}
