package v.o.r.ecommerce.stores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import v.o.r.ecommerce.common.interfaces.stores.IStoreController;
import v.o.r.ecommerce.common.service.BaseServiceError;
import v.o.r.ecommerce.stores.dto.CreateStoreDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Validated
@Controller
@RestController
@Tag(name = "stores")
@RequestMapping("stores")
public class StoresController implements IStoreController{

    @Autowired
    private StoresService useService;

     @Operation(summary = "Save a store")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "store created"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody CreateStoreDto createStore) {
        try {
            useService.save(createStore);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (Exception e) {
            return BaseServiceError.handleException(e);
        }
        
    }
    
}
