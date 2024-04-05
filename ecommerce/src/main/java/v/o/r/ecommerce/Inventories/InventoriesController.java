package v.o.r.ecommerce.Inventories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import v.o.r.ecommerce.Inventories.dto.CreateInventory;
import v.o.r.ecommerce.common.interfaces.inventories.IInventoryController;
import v.o.r.ecommerce.common.service.BaseServiceError;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Validated
@Controller
@RestController
@Tag(name = "inventories")
@RequestMapping("inventories")
public class InventoriesController implements IInventoryController{
    
    @Autowired
    private InventoriesService inventoriesService;

    @Operation(summary = "Save inventory")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "inventory created"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody CreateInventory createInventory){
        try {
            inventoriesService.save(createInventory);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
           return BaseServiceError.handleException(e);
        }
        
    }
}
