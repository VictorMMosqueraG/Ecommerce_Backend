package v.o.r.ecommerce.methodOfPay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import v.o.r.ecommerce.common.interfaces.methodOfPay.IMethodOfPayController;
import v.o.r.ecommerce.common.service.BaseServiceError;
import v.o.r.ecommerce.methodOfPay.dto.CreateMethodOfPay;
import v.o.r.ecommerce.methodOfPay.dto.PaginationMethodOfPayDto;
import v.o.r.ecommerce.methodOfPay.entities.MethodOfPayEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;



@Validated
@RestController
@RequestMapping("MethodOfPay")
@Tag(name = "MethodOfPay")
public class MethodOfPayController implements IMethodOfPayController{
    
    @Autowired
    private MethodOfPayService methodOfPayService;

    @Operation(summary = "Save a MethodOfPay")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "MethodOfPay created"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody CreateMethodOfPay createMethodOfPay){
        try {
            methodOfPayService.save(createMethodOfPay);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return BaseServiceError.handleException(e);
        }
    }

    @Operation(summary = "Found a MethodOfPay")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found MethodOfPay", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MethodOfPayEntity.class))),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/find")
    public ResponseEntity<?> find(@ModelAttribute PaginationMethodOfPayDto paginationMethodOfPayDto){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                .body(methodOfPayService.find(paginationMethodOfPayDto));
        } catch (Exception e) {
            return BaseServiceError.handleException(e);
        }
    }    
}
