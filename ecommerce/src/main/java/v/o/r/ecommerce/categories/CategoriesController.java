package v.o.r.ecommerce.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import v.o.r.ecommerce.categories.dto.CategoryDto;
import v.o.r.ecommerce.common.interfaces.categories.ICategoryController;
import v.o.r.ecommerce.common.service.BaseServiceError;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
@RestController
@Tag(name = "category")
@RequestMapping("category")
public class CategoriesController implements ICategoryController {

    @Autowired
    private CategoriesService useService;

    @Operation(summary = "Save a Category")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Category created"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
  
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody CategoryDto createCategory) {
        try {
            useService.save(createCategory);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return BaseServiceError.handleException(e);
        }
    }

}
