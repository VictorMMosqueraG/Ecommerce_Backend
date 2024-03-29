package v.o.r.ecommerce.Products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import v.o.r.ecommerce.Products.Dto.ProductsDto;
import v.o.r.ecommerce.common.interfaces.products.IProductsController;
import v.o.r.ecommerce.common.service.BaseServiceError;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
@Controller
@RequestMapping("products")
public class ProductsController implements IProductsController{
    
    @Autowired
    private ProductsService productsService;

    @PostMapping("/save")
    
     
      public ResponseEntity<?> save(@RequestBody ProductsDto createProduct){
          try {
            productsService.save(createProduct);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return BaseServiceError.handleException(e);
        } 
    }

}
