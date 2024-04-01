package v.o.r.ecommerce.common.interfaces.categories;

import v.o.r.ecommerce.categories.dto.CategoryDto;
import v.o.r.ecommerce.categories.entities.CategoryEntity;

public interface ICategoryService {
    public CategoryEntity save(CategoryDto createCategory);
}
