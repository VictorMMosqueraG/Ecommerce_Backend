package v.o.r.ecommerce.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import v.o.r.ecommerce.inventories.InventoriesService;
import v.o.r.ecommerce.inventories.dto.CreateInventoryDto;
import v.o.r.ecommerce.inventories.entities.InventoryEntity;
import v.o.r.ecommerce.inventories.repositories.InventoryRepository;
import v.o.r.ecommerce.inventory.mockData.InventoryMockData;
import v.o.r.ecommerce.products.ProductsService;
import v.o.r.ecommerce.products.mockData.ProductsMockData;
import v.o.r.ecommerce.references.ReferenceService;
import v.o.r.ecommerce.references.mockData.ReferenceMockData;
import v.o.r.ecommerce.store.mockData.StoreMockData;
import v.o.r.ecommerce.stores.StoresService;

public class InventoryServiceTest {
    
    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private ProductsService productsService;

    @Mock
    private ReferenceService referenceService;

    @Mock
    private StoresService storesService;

    @InjectMocks
    private InventoriesService inventoriesService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //NOTE: Test Save

    @Test
    public void testSaveSuccessfully(){
        //Initialize variable
        CreateInventoryDto createInventoryDto 
            = InventoryMockData.createInventoryDto();
        InventoryEntity inventoryEntity = 
            InventoryMockData.createInvInventoryEntity(createInventoryDto);

        //Configure methods when called
        when(productsService.findByIdOrFail(
            createInventoryDto.idProduct)
        ).thenReturn(Optional.of(ProductsMockData.productsEntity()));    

        when(storesService.findByIdOrFail(
            createInventoryDto.idStore
        )).thenReturn(Optional.of(StoreMockData.storesEntity()));

        when(referenceService.findByIdOrFail(
            createInventoryDto.idReference
        )).thenReturn(Optional.of(ReferenceMockData.referenceEntity()));

        when(inventoryRepository.save(
            any(InventoryEntity.class)
        )).thenReturn(inventoryEntity);

        //Call method service
        InventoryEntity result = inventoriesService.save(createInventoryDto);

        //Assert
        assertEquals(inventoryEntity, result);

        //Verify
        verify(productsService).findByIdOrFail(createInventoryDto.idProduct);
        verify(storesService).findByIdOrFail(createInventoryDto.idStore);
        verify(referenceService).findByIdOrFail(createInventoryDto.idReference);
        verify(inventoryRepository).save(any(InventoryEntity.class));
    }
}
