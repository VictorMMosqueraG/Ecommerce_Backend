package v.o.r.ecommerce.methodOfPay;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import v.o.r.ecommerce.methodOfPay.dto.CreateMethodOfPayDto;
import v.o.r.ecommerce.methodOfPay.dto.PaginationMethodOfPayDto;
import v.o.r.ecommerce.methodOfPay.entities.MethodOfPayEntity;
import v.o.r.ecommerce.methodOfPay.mockData.MethodOfPayMockData;
import v.o.r.ecommerce.methodOfPay.repositories.MethodOfPayRepository;

public class MethodOfPayServiceTest {
    
    @Mock
    private MethodOfPayRepository methodOfPayRepository;

    @InjectMocks
    private MethodOfPayService methodOfPayService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //NOTE: Test save

    @Test
    public void testSaveSuccessfully(){
        //Initialize variable
        CreateMethodOfPayDto createMethodOfPayDto = MethodOfPayMockData.createMethodOfPayDto();
        MethodOfPayEntity methodOfPayEntity = MethodOfPayMockData.createMethodOfPayEntity(createMethodOfPayDto);

        //Configure method when called
        when(methodOfPayRepository.save(
            any(MethodOfPayEntity.class)
        )).thenReturn(methodOfPayEntity);

        //Call method service
        MethodOfPayEntity result = methodOfPayService.save(createMethodOfPayDto);

        //Asserts
        assertEquals(methodOfPayEntity.getName(), result.getName());
        assertEquals(methodOfPayEntity.getDescription(), result.getDescription());

        //Verify
        verify(methodOfPayRepository).save(any(MethodOfPayEntity.class));
    }

    @Test
    public void testSaveWithoutDescription(){
        //Initialize variable
        CreateMethodOfPayDto createMethodOfPayDto = MethodOfPayMockData.createMethodOfPayDto();
        createMethodOfPayDto.description=null;

        MethodOfPayEntity methodOfPayEntity = MethodOfPayMockData.createMethodOfPayEntity(createMethodOfPayDto);

        //Configure method when called
        when(methodOfPayRepository.save(
            any(MethodOfPayEntity.class)
        )).thenReturn(methodOfPayEntity);

        //Call method service
        MethodOfPayEntity result = methodOfPayService.save(createMethodOfPayDto);

        //Asserts
        assertEquals(methodOfPayEntity.getName(), result.getName());
        assertEquals(null, result.getDescription());

        //Verify
        verify(methodOfPayRepository).save(any(MethodOfPayEntity.class));
    }

    //NOTE: Test find


    @Test
    public void testFindSuccessfully(){
        //Initialize variable
        List<MethodOfPayEntity> methodOfPayEntities = MethodOfPayMockData.listMethodOfPay();
        PaginationMethodOfPayDto paginationMethodOfPayDto = new PaginationMethodOfPayDto();

        //Configure method when called
        when(methodOfPayRepository.findAll()).thenReturn(methodOfPayEntities);

        //Call method service
        List<Map<String, Object>> result = methodOfPayService.find(paginationMethodOfPayDto);

        
        Map<String, Object> response = result.get(0);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        //Asserts
        assertEquals("MethodOfPay", response.get("context"));
        assertEquals(2, response.get("total"));

        //Data 1
        assertEquals(1L, data.get(0).get("id"));
        assertEquals("name1", data.get(0).get("name"));
        assertEquals("description1", data.get(0).get("description"));
        
        //Data 2
        assertEquals(2L, data.get(1).get("id"));
        assertEquals("name2", data.get(1).get("name"));
        assertEquals("description2", data.get(1).get("description"));

        //Verify
        verify(methodOfPayRepository).findAll();
    }

    @Test
    public void testFindWithFlatten(){
        //Initialize variable
        List<MethodOfPayEntity> methodOfPayEntities = MethodOfPayMockData.listMethodOfPayFlatten();
        PaginationMethodOfPayDto paginationMethodOfPayDto = new PaginationMethodOfPayDto();
        paginationMethodOfPayDto.setFlatten(true);

        //Configure method when called
        when(methodOfPayRepository.findAll()).thenReturn(methodOfPayEntities);

        //Call method service
        List<Map<String, Object>> result = methodOfPayService.find(paginationMethodOfPayDto);

        
        Map<String, Object> response = result.get(0);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        //Asserts
        assertEquals("MethodOfPay", response.get("context"));
        assertEquals(2, response.get("total"));

        //Data 1
        assertEquals(1L, data.get(0).get("id"));
        assertEquals("name1", data.get(0).get("name"));
        assertEquals(null, data.get(0).get("description"));
        
        //Data 2
        assertEquals(2L, data.get(1).get("id"));
        assertEquals("name2", data.get(1).get("name"));
        assertEquals(null, data.get(1).get("description"));

        //Verify
        verify(methodOfPayRepository).findAll();
    }

    @Test
    public void testFIndErrorAdditionalField(){
        //Initialize variable
        PaginationMethodOfPayDto paginationMethodOfPayDto = new PaginationMethodOfPayDto();

        //Configure method when called
        when(methodOfPayRepository.findAll()).thenThrow(
            new IllegalArgumentException(
                "The PaginationMethodOfPayDto object cannot have other fields besides 'flatten'.")
        );

        //Asserts
        assertThrows(IllegalArgumentException.class, 
            ()-> methodOfPayService.find(paginationMethodOfPayDto));

        //Verify
        verify(methodOfPayRepository).findAll();
    }

    @Test
    public void testFindWithLimit(){
        //Initialize variable
        List<MethodOfPayEntity> entities = MethodOfPayMockData.listMethodOfPay();
        PaginationMethodOfPayDto paginationMethodOfPayDto = new PaginationMethodOfPayDto();
        paginationMethodOfPayDto.setLimit(1);

        //Configure method when called
        when(methodOfPayRepository.findAll()).thenReturn(entities);

        //Call method service
        List<Map<String, Object>> result = methodOfPayService.find(paginationMethodOfPayDto);

        
        Map<String, Object> response = result.get(0);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        //Asserts
        assertEquals("MethodOfPay", response.get("context"));
        assertEquals(2, response.get("total"));
        assertEquals(1L, data.get(0).get("id"));
        assertEquals("name1", data.get(0).get("name"));
        assertEquals("description1", data.get(0).get("description"));

        //Verify
        verify(methodOfPayRepository).findAll();
    }

    @Test
    public void testFindWithOffset(){
        //Initialize variable
        List<MethodOfPayEntity> entities = MethodOfPayMockData.listMethodOfPay();
        PaginationMethodOfPayDto paginationMethodOfPayDto = new PaginationMethodOfPayDto();
        paginationMethodOfPayDto.setOffset(1);

        //Configure method when called
        when(methodOfPayRepository.findAll()).thenReturn(entities);

        //Call method service
        List<Map<String, Object>> result = methodOfPayService.find(paginationMethodOfPayDto);

        
        Map<String, Object> response = result.get(0);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        //Asserts
        assertEquals("MethodOfPay", response.get("context"));
        assertEquals(2, response.get("total"));
        assertEquals(2L, data.get(0).get("id"));
        assertEquals("name2", data.get(0).get("name"));
        assertEquals("description2", data.get(0).get("description"));

        //Verify
        verify(methodOfPayRepository).findAll();
    }


      @Test
    public void testFindWithSortOrder(){
        //Initialize variable
        List<MethodOfPayEntity> methodOfPayEntities = MethodOfPayMockData.listMethodOfPay();
        PaginationMethodOfPayDto paginationMethodOfPayDto = new PaginationMethodOfPayDto();
        paginationMethodOfPayDto.setSortOrder("DESC");//COMEBACK: change this for enum

        //Configure method when called
        when(methodOfPayRepository.findAll()).thenReturn(methodOfPayEntities);

        //Call method service
        List<Map<String, Object>> result = methodOfPayService.find(paginationMethodOfPayDto);

        
        Map<String, Object> response = result.get(0);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        //Asserts
        assertEquals("MethodOfPay", response.get("context"));
        assertEquals(2, response.get("total"));

        //Data 1
        assertEquals(1L, data.get(1).get("id"));
        assertEquals("name1", data.get(1).get("name"));
        assertEquals("description1", data.get(1).get("description"));
        
        //Data 2
        assertEquals(2L, data.get(0).get("id"));
        assertEquals("name2", data.get(0).get("name"));
        assertEquals("description2", data.get(0).get("description"));

        //Verify
        verify(methodOfPayRepository).findAll();
    }

    @Test
    public void testFindWithName(){
        //Initialize variable
        List<MethodOfPayEntity> entities = MethodOfPayMockData.listMethodOfPay();
        PaginationMethodOfPayDto paginationMethodOfPayDto = new PaginationMethodOfPayDto();
        paginationMethodOfPayDto.setName("name1");;

        //Configure method when called
        when(methodOfPayRepository.findAll()).thenReturn(entities);

        //Call method service
        List<Map<String, Object>> result = methodOfPayService.find(paginationMethodOfPayDto);

        
        Map<String, Object> response = result.get(0);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        //Asserts
        assertEquals("MethodOfPay", response.get("context"));
        assertEquals(2, response.get("total"));
        assertEquals(1L, data.get(0).get("id"));
        assertEquals("name1", data.get(0).get("name"));
        assertEquals("description1", data.get(0).get("description"));

        //Verify
        verify(methodOfPayRepository).findAll();
    }

}
