package v.o.r.ecommerce.products.mockData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import v.o.r.ecommerce.categories.entities.CategoryEntity;
import v.o.r.ecommerce.category.mockData.CategoryMockData;
import v.o.r.ecommerce.common.enums.EnumUtils;
import v.o.r.ecommerce.products.dto.CreateProductDto;
import v.o.r.ecommerce.products.entities.ProductsEntity;

public class ProductsMockData {
 
    //NOTE: methods for save
    public static CreateProductDto createProductDto(){
        CreateProductDto dto = new CreateProductDto();
        dto.name="name";
        dto.description="description";
        dto.categories=Arrays.asList(1L);
        dto.money=EnumUtils.COP;//FIX: changed name that enum for other
        dto.price="1.000.000";

        return dto;
    }

    public static ProductsEntity createProductsEntity(
        CreateProductDto createProductDto,
        List<CategoryEntity> category
    ){
        ProductsEntity entity = new ProductsEntity();
        entity.setName(createProductDto.name);
        entity.setDescription(createProductDto.description);        
        entity.setCategories(category);
        entity.setMoney(createProductDto.money);
        entity.setPrice(createProductDto.price);

        return entity;
    }

  

    //NOTE: method for list
    public static List<ProductsEntity> listProducts(){
        ProductsEntity entity1 = new ProductsEntity();
        entity1.setId(1L);
        entity1.setName("name1");
        entity1.setDescription("description1");        
        entity1.setCategories(CategoryMockData.createCategoryEntity());
        entity1.setMoney(EnumUtils.COP);
        entity1.setPrice("price1");

        ProductsEntity entity2 = new ProductsEntity();
        entity2.setId(2L);
        entity2.setName("name2");
        entity2.setDescription("description2");        
        entity2.setCategories(CategoryMockData.createCategoryEntity());
        entity2.setMoney(EnumUtils.USD);
        entity2.setPrice("price2");

        List<ProductsEntity> products = Arrays.asList(entity1,entity2);

        return products;
    }

    public static List<ProductsEntity> listProductFlatten(){
        ProductsEntity entity1 = new ProductsEntity();
        entity1.setId(1L);
        entity1.setName("name1");
        
        ProductsEntity entity2 = new ProductsEntity();
        entity2.setId(2L);
        entity2.setName("name2");

        List<ProductsEntity> products = Arrays.asList(entity1,entity2);

        return products;
    }

    public static List<Map<String, Object>> ExpectedDataListProducts() {
        List<Map<String, Object>> productList = new ArrayList<>();

        for (ProductsEntity product : listProducts()) {
            Map<String, Object> productMap = new HashMap<>();
            productMap.put("id", product.getId());
            productMap.put("name", product.getName());
            productMap.put("description", product.getDescription());
            productMap.put("categories", product.getCategories());
            productMap.put("money", product.getMoney());
            productMap.put("price", product.getPrice());

            productList.add(productMap);
        }

        return productList;
    }

    //NOTE: method for other class
    public static ProductsEntity productsEntity(){
        ProductsEntity entity = new ProductsEntity();

        entity.setId(1L);
        entity.setName("name1");
        entity.setDescription("description1");        
        entity.setCategories(CategoryMockData.createCategoryEntity());
        entity.setMoney(EnumUtils.COP);
        entity.setPrice("price1");

        return entity;
    }
}
