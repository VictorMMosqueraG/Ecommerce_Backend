package v.o.r.ecommerce.inventories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import v.o.r.ecommerce.common.interfaces.inventories.IInventoryController;
import v.o.r.ecommerce.common.service.BaseServiceError;
import v.o.r.ecommerce.inventories.dto.CreateInventoryDto;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
@Validated
@Controller
@RestController
@Tag(name = "inventories")
@RequestMapping("inventories")
public class InventoriesController implements IInventoryController{
    
    @Autowired
    private InventoriesService inventoriesService;

    @Operation(summary = "Save a inventories")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201", 
            description = "Product created",
            content = @Content(mediaType = "application/json")    
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "Bad request",
            content = @Content(mediaType = "application/json", 
                schema = @Schema(example = "{\"code\": \"BAD_REQUEST\", \"error\": \"Bad request\", \"message\": \"Invalid input data\" }"))
        ),
        @ApiResponse(
            responseCode = "500", 
            description = "Internal Server Error",
            content = @Content(mediaType = "application/json", 
            schema = @Schema(example = "{\"code\": \"UNEXPECTED_ERROR\", \"error\": \"Internal Server Error\", \"message\": \"Unexpected Error\" }"))    
        )
    })
    @PreAuthorize("hasAnyAuthority('Inventory.write.all', 'Inventory.write')")
    @PostMapping
    public ResponseEntity<?> save(@RequestBody CreateInventoryDto createInventory){
        try {
            inventoriesService.save(createInventory);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
           return BaseServiceError.handleException(e);
        }
        
    }
}
