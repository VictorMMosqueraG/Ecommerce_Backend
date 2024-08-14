package v.o.r.ecommerce.category.mockData;

import java.util.ArrayList;
import java.util.List;

import v.o.r.ecommerce.categories.dto.CreateCategoryDto;
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

    //For method save
    public static CreateCategoryDto createCategoryDto(){
        CreateCategoryDto dto = new CreateCategoryDto();
        dto.name="name";
        dto.description="description";

        return dto;
    }

    public static CategoryEntity categoryEntity(
        CreateCategoryDto createCategoryDto
    ){
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(createCategoryDto.name);
        categoryEntity.setDescription(createCategoryDto.description);

        return categoryEntity;
    }
}
