package v.o.r.ecommerce.persons;

import java.util.List;
import java.util.Map;

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
import v.o.r.ecommerce.common.interfaces.persons.IPersonController;
import v.o.r.ecommerce.common.service.BaseServiceError;
import v.o.r.ecommerce.persons.dto.CreatePerson;
import v.o.r.ecommerce.persons.dto.PaginationPersonDto;
import v.o.r.ecommerce.persons.entities.PersonEntity;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;




@Validated
@RestController
@RequestMapping("person")
@Tag(name = "persons")
public class PersonController implements IPersonController{

    @Autowired
    private PersonService personService;

    @Operation(summary = "Save a person")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201", 
            description = "Person created",
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
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody CreatePerson createPerson){
        try {
         personService.save(createPerson);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return BaseServiceError.handleException(e);
        }
    }

    @Operation(summary = "Found a person")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Found person", 
            content = @Content(mediaType = "application/json",schema = @Schema(implementation = PersonEntity.class))
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
    @GetMapping("/find")
    public ResponseEntity<?> find(@ModelAttribute PaginationPersonDto paginationPersonDto){
        try {
            List<Map<String,Object>> foundPerson = personService.find(paginationPersonDto);
            return ResponseEntity.status(HttpStatus.OK).body(foundPerson);
        } catch (Exception e) {
            return BaseServiceError.handleException(e);
        }
    }
}
