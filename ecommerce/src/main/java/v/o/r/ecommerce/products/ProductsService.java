package v.o.r.ecommerce.products;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import v.o.r.ecommerce.categories.CategoriesService;
import v.o.r.ecommerce.categories.entities.CategoryEntity;
import v.o.r.ecommerce.common.interfaces.products.IProductsService;
import v.o.r.ecommerce.products.dto.PaginationProductDto;
import v.o.r.ecommerce.products.dto.CreateProductDto;
import v.o.r.ecommerce.products.entities.ProductsEntity;
import v.o.r.ecommerce.products.repositories.ProductRepository;

@Service
public class ProductsService implements IProductsService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoriesService categoriesService;

    //COMEBACK: test this and do refactor
    public ProductsEntity save(CreateProductDto createProducts) {
        ProductsEntity product = new ProductsEntity();

        product.setName(createProducts.name);
        product.setDescription(createProducts.description);
        product.setMoney(createProducts.money);
        product.setPrice(createProducts.price);

        if (createProducts.categories.isEmpty() || createProducts.categories == null) {
            throw new IllegalArgumentException("Categories can be null or empty");
        }

        List<CategoryEntity> categoriesList = new ArrayList<>();
        for (int i = 0; i < createProducts.categories.size(); i++) {
            categoriesList.add(categoriesService.findByIdOrFail(createProducts.categories.get(i)));
        }

        product.setCategories(categoriesList);

        return productRepository.save(product);
    }

    public List<Map<String, Object>> find(PaginationProductDto paginationProductDto) {
        List<ProductsEntity> listProduct = productRepository.findAll();

        boolean flatten = paginationProductDto != null && paginationProductDto.isFlatten();
        int limit = paginationProductDto != null && paginationProductDto.getLimit() != 0
                ? paginationProductDto.getLimit()
                : 50;
        int offset = paginationProductDto != null && paginationProductDto.getOffset() != 0
                ? paginationProductDto.getOffset()
                : 0;
        String sortOrder = paginationProductDto != null ? paginationProductDto.getSortOrder() : null;
        String name = paginationProductDto != null ? paginationProductDto.getName() : null;
        String price = paginationProductDto != null ? paginationProductDto.getPrice() : null;
        String nameCategories = paginationProductDto != null ? paginationProductDto.getNameCategories() : null;

        if (flatten == true) {
            if (limit != 50 || offset != 0 || name != null || price != null || nameCategories != null) {
                throw new IllegalArgumentException(
                        "The PaginationUserDto object cannot have other fields besides 'flatten'.");
            }

            Map<String, Object> response = new LinkedHashMap<>();
            response.put("context", "products");
            response.put("total", listProduct.size());
            response.put("data", listProduct.stream()
                    .map(product -> {
                        Map<String, Object> productMap = new LinkedHashMap<>();
                        productMap.put("id", product.getId());
                        productMap.put("name", product.getName());
                        return productMap;
                    })
                    .collect(Collectors.toList()));

            return Collections.singletonList(response);
        }

        Comparator<Map<String, Object>> sort = Comparator.comparing(m -> (String) m.get("name"));

        if (sortOrder != null && sortOrder.equalsIgnoreCase("desc")) {
            sort = sort.reversed();
        }

        Predicate<ProductsEntity> nameFind = product -> name == null
        || product.getName().toLowerCase().contains(name.toLowerCase());
        Predicate<ProductsEntity> priceFilter = product -> price == null
                || product.getPrice().toLowerCase().contains(price.toLowerCase());
        Predicate<ProductsEntity> categoryFilter = product -> nameCategories == null
                || product.getCategories().stream()
                .map(CategoryEntity::getName)
                .anyMatch(categoryName -> categoryName.equalsIgnoreCase(nameCategories.toLowerCase()));

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("context", "products");
        response.put("total", listProduct.size());
        response.put("data", listProduct.stream()
                .filter(priceFilter.and(categoryFilter).and(nameFind))
                .map(product -> {
                    Map<String, Object> productMap = new LinkedHashMap<>();
                    productMap.put("id", product.getId());
                    productMap.put("name", product.getName());
                    productMap.put("description", product.getDescription());
                    productMap.put("price", product.getPrice());
                    productMap.put("money", product.getMoney());
                    productMap.put("categories", product.getCategories().stream()
                    .map(CategoryEntity::getName)
                    .collect(Collectors.toList()));
                    return productMap;
                })
                .sorted(sort)
                .skip(offset)
                .limit(limit)
                .collect(Collectors.toList()));

        List<Map<String, Object>> resultList = new ArrayList<>();
        resultList.add(response);
        return resultList;
    }
    public Optional<ProductsEntity> findById(Long id){
        return productRepository.findById(id);
    }

    public Optional<ProductsEntity> findByIdOrFail(Long id){
        Optional<ProductsEntity> newLink = id!=null ? findById(id):null;

        if(newLink==null || newLink.isEmpty()){
            throw new NoSuchElementException("Product with id " + id + " not found.");
        }

        return newLink;
    }
}