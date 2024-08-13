package v.o.r.ecommerce.references;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import v.o.r.ecommerce.references.dto.CreateReferenceDto;
import v.o.r.ecommerce.references.entities.ReferenceEntity;
import v.o.r.ecommerce.references.mockData.ReferenceMockData;
import v.o.r.ecommerce.references.repositories.ReferenceRepository;

public class ReferenceServiceTest {
    
    @Mock
    private ReferenceRepository referenceRepository;

    @InjectMocks
    private ReferenceService referenceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //NOTE: Test save


    @Test
    public void testSaveSuccessfully(){
        //Initialize variable
        CreateReferenceDto createReferenceDto 
            = ReferenceMockData.createReferenceDto();
        ReferenceEntity referenceEntity 
            = ReferenceMockData.createReferenceEntity(createReferenceDto);

        //Configure method when called
        when(referenceRepository.save(
            any(ReferenceEntity.class)
        )).thenReturn(referenceEntity);

        //Call method called
        ReferenceEntity result = referenceService.save(createReferenceDto);

        //Assets
        assertEquals(referenceEntity, result);

        //Verify
        verify(referenceRepository).save(any(ReferenceEntity.class));
    }
}
