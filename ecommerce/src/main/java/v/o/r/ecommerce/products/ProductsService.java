package v.o.r.ecommerce.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import v.o.r.ecommerce.categories.entities.CategoryEntity;
import v.o.r.ecommerce.categories.repositories.CategoryRepository;
import v.o.r.ecommerce.common.interfaces.products.IProductsService;
import v.o.r.ecommerce.products.dto.ProductsDto;
import v.o.r.ecommerce.products.entities.ProductsEntity;
import v.o.r.ecommerce.products.repositories.ProductRepository;

@Service
public class ProductsService implements IProductsService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ProductsEntity save(ProductsDto createProducts) {
        ProductsEntity product = new ProductsEntity();

        product.setName(createProducts.name);
        product.setDescription(createProducts.description);
        product.setMoney(createProducts.money);
        product.setPrice(createProducts.price);

        if (createProducts.categories.isEmpty() || createProducts.categories == null) {
            throw new IllegalArgumentException("Categories can be null or empty");
        }
        int index = 1;
        for (int i = 0; i < createProducts.categories.size(); i++) {
            Optional<CategoryEntity> categories = categoryRepository.findById(createProducts.categories.get(i));
            if (!categories.isPresent()) {
                throw new IllegalArgumentException("The value with index: " + index +
                        " is not found in the database. Please verify if it was entered correctly.");
            }
            index++;
        }
        product.setCategory(createProducts.categories);

        return productRepository.save(product);

    }

}