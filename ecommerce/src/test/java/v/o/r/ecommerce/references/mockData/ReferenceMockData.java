package v.o.r.ecommerce.references.mockData;

import v.o.r.ecommerce.references.dto.CreateReferenceDto;
import v.o.r.ecommerce.references.entities.ReferenceEntity;

public class ReferenceMockData {

    
    //NOTE: methods for save
    public static CreateReferenceDto createReferenceDto(){
        CreateReferenceDto dto = new CreateReferenceDto();
        dto.name="References";
        dto.description="Description";

        return dto;
    }

    public static ReferenceEntity createReferenceEntity(
        CreateReferenceDto  createReferenceDto
    ){
        ReferenceEntity entity = new ReferenceEntity();
        entity.setId(1L);
        entity.setName(createReferenceDto.name);
        entity.setDescription(createReferenceDto.description);

        return entity;
    }

    //NOTE: methods for other class
    public static ReferenceEntity referenceEntity(){
        ReferenceEntity entity = new ReferenceEntity();
        entity.setId(1L);
        entity.setName("name");
        entity.setDescription("description");

        return entity;
    }

}
