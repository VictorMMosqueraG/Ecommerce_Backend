package v.o.r.ecommerce.category.mockData;

import java.util.ArrayList;
import java.util.List;

import v.o.r.ecommerce.categories.entities.CategoryEntity;

public class CategoryMockData {
    

    //Method for other class
    public static List<CategoryEntity> createCategoryEntity(){
        CategoryEntity dto = new CategoryEntity();
        dto.setName("name");
        dto.setDescription("description");
        
        List<CategoryEntity> listCategory = new ArrayList<>();
        listCategory.add(dto);

        return listCategory;
    }
}
