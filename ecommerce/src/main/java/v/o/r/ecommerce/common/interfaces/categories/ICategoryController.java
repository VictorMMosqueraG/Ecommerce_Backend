package v.o.r.ecommerce.common.interfaces.categories;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import v.o.r.ecommerce.categories.dto.CategoryDto;

public interface ICategoryController {
    public ResponseEntity<?> save(@RequestBody CategoryDto createCategory);
}
