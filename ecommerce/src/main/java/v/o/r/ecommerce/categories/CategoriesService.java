package v.o.r.ecommerce.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import v.o.r.ecommerce.categories.dto.CategoryDto;
import v.o.r.ecommerce.categories.entities.CategoryEntity;
import v.o.r.ecommerce.categories.repositories.CategoryRepository;
import v.o.r.ecommerce.common.interfaces.categories.ICategoryService;

@Service
public class CategoriesService implements ICategoryService {
    
    @Autowired
    private CategoryRepository useRepository;

    public CategoryEntity save(CategoryDto createCategory){
        CategoryEntity category = new CategoryEntity();
        
        category.setName(createCategory.name);
        category.setDescription(createCategory.description);
        return useRepository.save(category);
    }
}
