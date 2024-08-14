package v.o.r.ecommerce.category;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import v.o.r.ecommerce.categories.CategoriesService;
import v.o.r.ecommerce.categories.dto.CreateCategoryDto;
import v.o.r.ecommerce.categories.entities.CategoryEntity;
import v.o.r.ecommerce.categories.repositories.CategoryRepository;
import v.o.r.ecommerce.category.mockData.CategoryMockData;

public class CategoryServiceTest {
    
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoriesService categoriesService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //NOTE: Save Test
    @Test
    public void testSaveSuccessfully(){
        //Initialize variable
        CreateCategoryDto createCategoryDto = CategoryMockData.createCategoryDto();
        CategoryEntity categoryEntity = CategoryMockData.categoryEntity(createCategoryDto);

        //Configure method when called
        when(categoryRepository.save(
            any(CategoryEntity.class)
        )).thenReturn(categoryEntity);

        //Call method service
        CategoryEntity result = categoriesService.save(createCategoryDto);

        //Asserts
        assertEquals(categoryEntity, result);

        //Verify
        verify(categoryRepository).save(any(CategoryEntity.class));
    }
}
