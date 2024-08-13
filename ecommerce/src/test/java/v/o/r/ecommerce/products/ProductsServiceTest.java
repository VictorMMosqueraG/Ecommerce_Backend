package v.o.r.ecommerce.products;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import v.o.r.ecommerce.categories.CategoriesService;
import v.o.r.ecommerce.categories.entities.CategoryEntity;
import v.o.r.ecommerce.category.mockData.CategoryMockData;
import v.o.r.ecommerce.common.enums.EnumUtils;
import v.o.r.ecommerce.products.dto.CreateProductDto;
import v.o.r.ecommerce.products.dto.PaginationProductDto;
import v.o.r.ecommerce.products.entities.ProductsEntity;
import v.o.r.ecommerce.products.mockData.ProductsMockData;
import v.o.r.ecommerce.products.repositories.ProductRepository;

public class ProductsServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoriesService categoriesService;

    @InjectMocks
    private ProductsService productsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // NOTE: Test save

    @Test
    public void testSaveSuccessfully() {
        // Initialize variable
        CreateProductDto createProductDto = ProductsMockData.createProductDto();
        List<CategoryEntity> category = CategoryMockData.createCategoryEntity();
        ProductsEntity productsEntity = ProductsMockData.createProductsEntity(createProductDto, category);

        // Configure method when called
        when(categoriesService.findByIdOrFail(
                createProductDto.categories.get(0))).thenReturn(category.get(0));

        when(productRepository.save(
                any(ProductsEntity.class))).thenReturn(productsEntity);

        // Call method service
        ProductsEntity result = productsService.save(createProductDto);

        // Asserts
        assertEquals(productsEntity, result);

        // Verify
        verify(categoriesService).findByIdOrFail(createProductDto.categories.get(0));
        verify(productRepository).save(any(ProductsEntity.class));
    }

    // NOTE: Test find
    @Test
    public void testFindSuccessfully() {
        // Initialize variable
        List<ProductsEntity> productsEntities = ProductsMockData.listProducts();
        PaginationProductDto paginationProductDto = new PaginationProductDto();

        // Configure method when called
        when(productRepository.findAll()).thenReturn(productsEntities);

        // Call method service
        List<Map<String, Object>> result = productsService.find(paginationProductDto);

        Map<String, Object> response = result.get(0);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        // Asserts
        assertEquals("products", response.get("context"));
        assertEquals(2, response.get("total"));
        // Valid data 1
        assertEquals(1L, data.get(0).get("id"));
        assertEquals("name1", data.get(0).get("name"));
        assertEquals("description1", data.get(0).get("description"));
        assertEquals(
                Arrays.asList(
                        productsEntities.get(0)
                                .getCategories()
                                .get(0)
                                .getName()),
                data.get(0).get("categories"));
        assertEquals(EnumUtils.COP, data.get(0).get("money"));
        assertEquals("price1", data.get(0).get("price"));

        // Valid data 2
        assertEquals(2L, data.get(1).get("id"));
        assertEquals("name2", data.get(1).get("name"));
        assertEquals("description2", data.get(1).get("description"));
        assertEquals(
                Arrays.asList(
                        productsEntities.get(0)
                                .getCategories()
                                .get(0)
                                .getName()),
                data.get(0).get("categories"));
        assertEquals(EnumUtils.USD, data.get(1).get("money"));
        assertEquals("price2", data.get(1).get("price"));

        // Verify
        verify(productRepository).findAll();
    }

    @Test
    public void testFindWithFlatten() {
        // Initialize variable
        List<ProductsEntity> productsEntities = ProductsMockData.listProductFlatten();
        PaginationProductDto paginationProductDto = new PaginationProductDto();
        paginationProductDto.setFlatten(true);

        // Configure method when called
        when(productRepository.findAll()).thenReturn(productsEntities);

        // Call method service
        List<Map<String, Object>> result = productsService.find(paginationProductDto);

        Map<String, Object> response = result.get(0);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        // Asserts
        assertEquals("products", response.get("context"));
        assertEquals(2, response.get("total"));
        // Valid data 1
        assertEquals(1L, data.get(0).get("id"));
        assertEquals("name1", data.get(0).get("name"));
        assertEquals(null, data.get(0).get("description"));
        assertEquals(null, data.get(0).get("categories"));
        assertEquals(null, data.get(0).get("money"));
        assertEquals(null, data.get(0).get("price"));

        // Valid data 2
        assertEquals(2L, data.get(1).get("id"));
        assertEquals("name2", data.get(1).get("name"));
        assertEquals(null, data.get(1).get("description"));
        assertEquals(null, data.get(0).get("categories"));
        assertEquals(null, data.get(1).get("money"));
        assertEquals(null, data.get(1).get("price"));

        // Verify
        verify(productRepository).findAll();
    }

    @Test
    public void testFindWithErrorFlatten() {
        // Initialize variable
        PaginationProductDto paginationProductDto = new PaginationProductDto();

        // Configure method when called
        when(productRepository.findAll()).thenThrow(
                new IllegalArgumentException(
                        "The PaginationProductsDto object cannot have other fields besides 'flatten'."));

        // Asserts
        assertThrows(IllegalArgumentException.class, () -> productsService.find(paginationProductDto));
    }

    @Test
    public void testFindWithLimit() {
        // Initialize variable
        List<ProductsEntity> productsEntities = ProductsMockData.listProducts();
        PaginationProductDto paginationProductDto = new PaginationProductDto();
        paginationProductDto.setLimit(1);

        // Configure method when called
        when(productRepository.findAll()).thenReturn(productsEntities);

        // Call method service
        List<Map<String, Object>> result = productsService.find(paginationProductDto);

        Map<String, Object> response = result.get(0);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        // Asserts
        assertEquals("products", response.get("context"));
        assertEquals(2, response.get("total"));

        // Valid data 1
        assertEquals(1L, data.get(0).get("id"));
        assertEquals("name1", data.get(0).get("name"));
        assertEquals("description1", data.get(0).get("description"));
        assertEquals(
                Arrays.asList(
                        productsEntities.get(0)
                                .getCategories()
                                .get(0)
                                .getName()),
                data.get(0).get("categories"));
        assertEquals(EnumUtils.COP, data.get(0).get("money"));
        assertEquals("price1", data.get(0).get("price"));

        // Verify
        verify(productRepository).findAll();
    }

    @Test
    public void testFindWithOffset() {
        // Initialize variable
        List<ProductsEntity> productsEntities = ProductsMockData.listProducts();
        PaginationProductDto paginationProductDto = new PaginationProductDto();
        paginationProductDto.setOffset(1);

        // Configure method when called
        when(productRepository.findAll()).thenReturn(productsEntities);

        // Call method service
        List<Map<String, Object>> result = productsService.find(paginationProductDto);

        Map<String, Object> response = result.get(0);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        // Asserts
        assertEquals("products", response.get("context"));
        assertEquals(2, response.get("total"));
        // Valid data 1
        assertEquals(2L, data.get(0).get("id"));
        assertEquals("name2", data.get(0).get("name"));
        assertEquals("description2", data.get(0).get("description"));
        assertEquals(
                Arrays.asList(
                        productsEntities.get(0)
                                .getCategories()
                                .get(0)
                                .getName()),
                data.get(0).get("categories"));
        assertEquals(EnumUtils.USD, data.get(0).get("money"));
        assertEquals("price2", data.get(0).get("price"));

        // Verify
        verify(productRepository).findAll();
    }

    @Test
    public void testFindWithSortOrder() {
        // Initialize variable
        List<ProductsEntity> productsEntities = ProductsMockData.listProducts();
        PaginationProductDto paginationProductDto = new PaginationProductDto();
        paginationProductDto.setSortOrder("DESC");// COMEBACK: use enum

        // Configure method when called
        when(productRepository.findAll()).thenReturn(productsEntities);

        // Call method service
        List<Map<String, Object>> result = productsService.find(paginationProductDto);

        Map<String, Object> response = result.get(0);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        // Asserts
        assertEquals("products", response.get("context"));
        assertEquals(2, response.get("total"));
        // Valid data 1
        assertEquals(2L, data.get(0).get("id"));
        assertEquals("name2", data.get(0).get("name"));
        assertEquals("description2", data.get(0).get("description"));
        assertEquals(
                Arrays.asList(
                        productsEntities.get(0)
                                .getCategories()
                                .get(0)
                                .getName()),
                data.get(0).get("categories"));
        assertEquals(EnumUtils.USD, data.get(0).get("money"));
        assertEquals("price2", data.get(0).get("price"));

        // Valid data 2
        assertEquals(1L, data.get(1).get("id"));
        assertEquals("name1", data.get(1).get("name"));
        assertEquals("description1", data.get(1).get("description"));
        assertEquals(
                Arrays.asList(
                        productsEntities.get(0)
                                .getCategories()
                                .get(0)
                                .getName()),
                data.get(0).get("categories"));
        assertEquals(EnumUtils.COP, data.get(1).get("money"));
        assertEquals("price1", data.get(1).get("price"));

        // Verify
        verify(productRepository).findAll();
    }

    @Test
    public void testFindWithName() {
        // Initialize variable
        List<ProductsEntity> productsEntities = ProductsMockData.listProducts();
        PaginationProductDto paginationProductDto = new PaginationProductDto();
        paginationProductDto.setName("name1");

        // Configure method when called
        when(productRepository.findAll()).thenReturn(productsEntities);

        // Call method service
        List<Map<String, Object>> result = productsService.find(paginationProductDto);

        Map<String, Object> response = result.get(0);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        // Asserts
        assertEquals("products", response.get("context"));
        assertEquals(2, response.get("total"));
        // Valid data 1
        assertEquals(1L, data.get(0).get("id"));
        assertEquals("name1", data.get(0).get("name"));
        assertEquals("description1", data.get(0).get("description"));
        assertEquals(
                Arrays.asList(
                        productsEntities.get(0)
                                .getCategories()
                                .get(0)
                                .getName()),
                data.get(0).get("categories"));
        assertEquals(EnumUtils.COP, data.get(0).get("money"));
        assertEquals("price1", data.get(0).get("price"));

        // Verify
        verify(productRepository).findAll();
    }

    @Test
    public void testFindWithPrice() {
        // Initialize variable
        List<ProductsEntity> productsEntities = ProductsMockData.listProducts();
        PaginationProductDto paginationProductDto = new PaginationProductDto();
        paginationProductDto.setPrice("price1");

        // Configure method when called
        when(productRepository.findAll()).thenReturn(productsEntities);

        // Call method service
        List<Map<String, Object>> result = productsService.find(paginationProductDto);

        Map<String, Object> response = result.get(0);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        // Asserts
        assertEquals("products", response.get("context"));
        assertEquals(2, response.get("total"));
        // Valid data 1
        assertEquals(1L, data.get(0).get("id"));
        assertEquals("name1", data.get(0).get("name"));
        assertEquals("description1", data.get(0).get("description"));
        assertEquals(
                Arrays.asList(
                        productsEntities.get(0)
                                .getCategories()
                                .get(0)
                                .getName()),
                data.get(0).get("categories"));
        assertEquals(EnumUtils.COP, data.get(0).get("money"));
        assertEquals("price1", data.get(0).get("price"));

        // Verify
        verify(productRepository).findAll();
    }

    //COMEBACK: missing test find with nameCategory
}
