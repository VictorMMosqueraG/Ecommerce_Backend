package v.o.r.ecommerce.references;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import v.o.r.ecommerce.common.interfaces.references.IReferenceController;
import v.o.r.ecommerce.common.service.BaseServiceError;
import v.o.r.ecommerce.references.dto.CreateReferenceDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Validated
@Controller
@Tag(name = "references")
@RequestMapping("references")
public class ReferenceController implements IReferenceController {
    
    @Autowired
    ReferenceService useService;


    @Operation(summary = "Save a references")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201", 
            description = "References created",
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
    @PreAuthorize("hasAnyAuthority('Reference.write.all','Reference.write')")
    @PostMapping
    public ResponseEntity<?> save(@RequestBody CreateReferenceDto createReference) {
       try {
            useService.save(createReference);
           return ResponseEntity.status(HttpStatus.CREATED).build();
        
       } catch (Exception e) {
        return BaseServiceError.handleException(e);
       }
    }
    
}
