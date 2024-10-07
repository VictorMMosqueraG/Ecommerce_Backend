package v.o.r.ecommerce.stores;


import java.util.List;
import java.util.Map;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;

import v.o.r.ecommerce.common.interfaces.stores.IStoreController;
import v.o.r.ecommerce.common.service.BaseServiceError;
import v.o.r.ecommerce.stores.dto.CreateStoreDto;
import v.o.r.ecommerce.stores.dto.PaginationStoreDto;
import v.o.r.ecommerce.stores.dto.UpdateStoreDto;
import v.o.r.ecommerce.stores.entities.StoresEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;





@Validated
@RestController
@Tag(name = "stores")
@RequestMapping("stores")
public class StoresController implements IStoreController{

    @Autowired
    private StoresService storesService;

    @Operation(summary = "Save a store")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201", 
            description = "store created",
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
    @PreAuthorize("hasAnyAuthority('Store.write.all','Store.write')")
    @PostMapping
    public ResponseEntity<?> save(@RequestBody CreateStoreDto createStore) {
        try {
            storesService.save(createStore);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return BaseServiceError.handleException(e);
        }
    }

    @Operation(summary = "Found a user")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Found user", 
            content = @Content(mediaType = "application/json", 
                schema = @Schema(example = "[\n{\n  \"context\": \"user\",\n  \"total\": 0,\n  \"data\": []\n}\n]"))
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
    @PreAuthorize("hasAuthority('Store.read')")
    @GetMapping
    public ResponseEntity<?> find(
        @ParameterObject 
        @ModelAttribute 
        PaginationStoreDto paginationStoreDto
    ) {
        try {
            List<Map<String,Object>> foundStore = storesService.find(paginationStoreDto);
            return ResponseEntity.status(HttpStatus.OK).body(foundStore);
        } catch (Exception e) {
            return BaseServiceError.handleException(e);
        }
    }

    @Operation(summary = "Update a store")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "store update",
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
    @PreAuthorize("hasAuthority('Store.write.all')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
        @PathVariable Long id,
        @RequestBody UpdateStoreDto updateStoreDto
    ) {
        try {
            StoresEntity updateStore  =  storesService.update(id, updateStoreDto);
            return ResponseEntity.status(HttpStatus.OK).body(updateStore);
        } catch (Exception e) {
            return BaseServiceError.handleException(e);
        }        
    }
}
