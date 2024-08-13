package v.o.r.ecommerce.common.interfaces.categories;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import v.o.r.ecommerce.categories.dto.CreateCategoryDto;

public interface ICategoryController {
    public ResponseEntity<?> save(@RequestBody CreateCategoryDto createCategory);
}
