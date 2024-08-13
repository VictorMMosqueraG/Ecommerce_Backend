package v.o.r.ecommerce.categories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import v.o.r.ecommerce.categories.dto.CreateCategoryDto;
import v.o.r.ecommerce.categories.entities.CategoryEntity;
import v.o.r.ecommerce.categories.repositories.CategoryRepository;
import v.o.r.ecommerce.common.interfaces.categories.ICategoryService;

@Service
public class CategoriesService implements ICategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryEntity save(CreateCategoryDto createCategory){
        CategoryEntity category = new CategoryEntity();
        
        category.setName(createCategory.name);
        category.setDescription(createCategory.description);
        return categoryRepository.save(category);
    }
    public CategoryEntity findByIdOrFail(long id){
        Optional<CategoryEntity> product_category = this.findById(id);
        if (product_category.isEmpty()) {
            throw new IllegalArgumentException("the category id: "+id+ "\n is not registered in the database. Please verify.");
        }
        CategoryEntity newLink = product_category.get();
        return newLink;
    }

    public Optional<CategoryEntity> findById(long id){
        Optional<CategoryEntity> product_category = categoryRepository.findById(id);
        return product_category;
    }
}
