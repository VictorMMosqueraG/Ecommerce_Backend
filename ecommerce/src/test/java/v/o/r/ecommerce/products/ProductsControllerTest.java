package v.o.r.ecommerce.products;


import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import v.o.r.ecommerce.products.entities.ProductsEntity;
import v.o.r.ecommerce.products.mockData.ProductsMockData;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductsControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductsService productsService;

    //NOTE: Test save products

    @Test
    @WithMockUser(authorities = "Product.write.all")
    public void testSaveProductsSuccessfully() throws Exception{
        String json = objectMapper.writeValueAsString(ProductsMockData.createProductDto());

        Mockito.when(productsService.save(any())).thenReturn(new ProductsEntity());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers
            .status()
            .isCreated()); 
    }

    @Test
    @WithMockUser(authorities = "Product.write.all")
    public void testSaveProductsAlreadyExist() throws Exception{
        String json = objectMapper.writeValueAsString(ProductsMockData.createProductDto());

        Mockito.when(productsService.save(any())).thenThrow(new DataIntegrityViolationException("Key (name)=(Reference) already exists.]"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers
            .status()
            .isBadRequest()); 
    }

    @Test
    @WithMockUser(authorities = "Product.write.all")
    public void testSaveProductsUnexpectedError() throws Exception{
        String json = objectMapper.writeValueAsString(ProductsMockData.createProductDto());

        Mockito.when(productsService.save(any())).thenThrow(new RuntimeException());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers
            .status()
            .isInternalServerError());
    }

    //NOTE: Test List Products
    @Test
    @WithMockUser(authorities = "Product.read")
    public void testFindProductsSuccessfully() throws Exception{
        String json = objectMapper.writeValueAsString(ProductsMockData.listProducts());

       
        Mockito.when(productsService.find(any())).thenReturn(ProductsMockData.ExpectedDataListProducts());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().json(json))
            .andExpect(MockMvcResultMatchers
            .status().isOk());       
    }

    @Test
    @WithMockUser(authorities = "Product.read")
    public void testFindProductsUnexpectedError() throws Exception{
        Mockito.when(productsService.find(any())).thenThrow(new RuntimeException());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers
            .status().isInternalServerError());  
    }

    @Test
    @WithMockUser(authorities = "Product.read")
    public void testFindProductsAdditionalParams() throws Exception{
        Mockito.when(productsService.find(any())).thenThrow(
            new IllegalArgumentException("The PaginationProductsDto object cannot have other fields besides 'flatten'.")
        );

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers
            .status().isBadRequest());  
    }

    
}
