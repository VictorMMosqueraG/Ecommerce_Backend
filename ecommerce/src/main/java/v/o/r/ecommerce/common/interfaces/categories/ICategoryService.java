package v.o.r.ecommerce.common.interfaces.categories;

import v.o.r.ecommerce.categories.dto.CreateCategoryDto;
import v.o.r.ecommerce.categories.entities.CategoryEntity;

public interface ICategoryService {
    public CategoryEntity save(CreateCategoryDto createCategory);
}
