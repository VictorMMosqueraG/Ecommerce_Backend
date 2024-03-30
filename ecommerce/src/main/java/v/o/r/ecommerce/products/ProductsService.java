package v.o.r.ecommerce.products;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import v.o.r.ecommerce.common.interfaces.products.IProductsService;
import v.o.r.ecommerce.products.dto.ProductsDto;
import v.o.r.ecommerce.products.entities.ProductsEntity;
import v.o.r.ecommerce.products.repositories.ProductRepository;

@Service
public class ProductsService implements IProductsService{


    @Autowired
    private ProductRepository productRepository;

    public ProductsEntity save(ProductsDto createProducts) {
        ProductsEntity product = new ProductsEntity();
        
        product.setName(createProducts.name);
        product.setDescription(createProducts.description);
        product.setCategories(createProducts.categories);
        product.setCategories(createProducts.categories);
        product.setMoney(createProducts.money);
  
            product.setPrice(createProducts.price);
        
        return productRepository.save(product);
        
    }
    
       
}