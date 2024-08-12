package v.o.r.ecommerce.permission;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.NoSuchElementException;

import v.o.r.ecommerce.permission.dto.CreatePermissionDto;
import v.o.r.ecommerce.permission.entities.PermissionEntity;
import v.o.r.ecommerce.permission.mockData.PermissionMockData;
import v.o.r.ecommerce.permission.repositories.PermissionRepository;

public class PermissionServiceTest {
    
    @Mock
    private PermissionRepository permissionRepository;

    @InjectMocks
    private PermissionService permissionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //NOTE: Save test
    
    @Test
    public void testSavePermissionSuccessfully(){
        //Initialize variable
        CreatePermissionDto createPermissionDto 
            = PermissionMockData.createPermissionDto();
        PermissionEntity permissionEntity 
            = PermissionMockData.permissionEntity(createPermissionDto);

        //Configure method service
        when(permissionRepository.save(
            any(PermissionEntity.class))
        ).thenReturn(permissionEntity);

        //Call method service
        PermissionEntity result = permissionService.save(createPermissionDto);


        //Asserts
        assertEquals(permissionEntity.getName(), result.getName());
        assertEquals(permissionEntity.getDescription(), result.getDescription());

        //Verify
        verify(permissionRepository).save(any(PermissionEntity.class));
    }

    @Test
    public void testSavePermissionWithoutDescription(){
        //Initialize variable
        CreatePermissionDto createPermissionDto 
            = PermissionMockData.createPermissionDto();
            createPermissionDto.description=null;
        PermissionEntity permissionEntity 
            = PermissionMockData.permissionEntity(createPermissionDto);

        //Configure method service
        when(permissionRepository.save(
            any(PermissionEntity.class))
        ).thenReturn(permissionEntity);

        //Call method service
        PermissionEntity result = permissionService.save(createPermissionDto);


        //Asserts
        assertEquals(permissionEntity.getName(), result.getName());
        assertEquals(permissionEntity.getDescription(), result.getDescription());

        //Verify
        verify(permissionRepository).save(any(PermissionEntity.class));
    }

    //NOTE: Find Detail Test
    @Test
    public void testFindDetailPermission(){
        //Initialize variable
        Long id = 1L;
        Optional<PermissionEntity> permissionEntity 
            = PermissionMockData.findDetail();

        //Configure method when called
        when(permissionRepository.findById(id)).thenReturn(permissionEntity);

        //Call method service
        Optional<PermissionEntity> result = permissionService.findDetail(id);

        //Asserts
        assertEquals(permissionEntity, result);

        //Verify
        verify(permissionRepository).findById(id);
    }

    @Test
    public void testFindDetailNotFound(){
        //Initialize variable
        Long id = 1L;
       
        //Configure method when called
        when(permissionRepository.findById(id))
            .thenThrow(new NoSuchElementException(
                "Permission with id " + id + " not found." 
            )
        );

        //Asserts
        assertThrows(NoSuchElementException.class, 
            ()-> permissionService.findDetail(id));

        //Verify
        verify(permissionRepository).findById(id);
    }
}
