package v.o.r.ecommerce.products;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import v.o.r.ecommerce.categories.CategoriesService;
import v.o.r.ecommerce.categories.entities.CategoryEntity;
import v.o.r.ecommerce.common.interfaces.products.IProductsService;
import v.o.r.ecommerce.products.dto.ProductsDto;
import v.o.r.ecommerce.products.entities.ProductsEntity;
import v.o.r.ecommerce.products.repositories.ProductRepository;

@Service
public class ProductsService implements IProductsService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoriesService categoriesService;

    public ProductsEntity save(ProductsDto createProducts) {
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
    public Optional<ProductsEntity> findByIdOrFail(Long id){
        Optional<ProductsEntity> foundProduct=id!=null ? this.findById(id):null;
        if (foundProduct==null || foundProduct.isEmpty()) {
            throw new NoSuchElementException("the Store: "+id+ "\n is not found.");
        }
       
        return foundProduct;
    }
    
    public Optional<ProductsEntity> findById(Long id){
        Optional<ProductsEntity> foundProduct = productRepository.findById(id);
        return foundProduct;
    }
}