package v.o.r.ecommerce.Products;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import v.o.r.ecommerce.Products.Dto.ProductsDto;
import v.o.r.ecommerce.Products.Entity.ProductsEntity;
import v.o.r.ecommerce.Products.Repositories.ProductsRepositories;
import v.o.r.ecommerce.common.interfaces.products.IProductsService;


@Service
public class ProductsService implements IProductsService{


    @Autowired
    private ProductsRepositories productsRepositories;

    public ProductsEntity save(ProductsDto createProducts) {
        ProductsEntity product = new ProductsEntity();
        
        product.setName(createProducts.name);
        product.setDescription(createProducts.description);
        product.setCategories(createProducts.categories);
        product.setCategories(createProducts.categories);
        product.setMoney(createProducts.money);
  
            product.setPrice(createProducts.price);
        
        return productsRepositories.save(product);
        
    }
    
       
}