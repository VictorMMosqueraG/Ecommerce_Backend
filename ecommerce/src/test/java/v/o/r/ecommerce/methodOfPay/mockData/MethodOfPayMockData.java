package v.o.r.ecommerce.methodOfPay.mockData;

import java.util.Arrays;
import java.util.List;

import v.o.r.ecommerce.methodOfPay.dto.CreateMethodOfPayDto;
import v.o.r.ecommerce.methodOfPay.entities.MethodOfPayEntity;

public class MethodOfPayMockData {
    
    //NOTE: methods for save

    public static CreateMethodOfPayDto createMethodOfPayDto(){
        CreateMethodOfPayDto dto = new CreateMethodOfPayDto();
        dto.name="name";
        dto.description="description";

        return dto;
    }

    public static MethodOfPayEntity createMethodOfPayEntity(
        CreateMethodOfPayDto createMethodOfPayDto
    ){
        MethodOfPayEntity entity = new MethodOfPayEntity();
        entity.setName(createMethodOfPayDto.name);
        entity.setDescription(createMethodOfPayDto.description);

        return entity;
    }

    //NOTE: methods for find
    public static List<MethodOfPayEntity> listMethodOfPay(){
        MethodOfPayEntity entity1 = new MethodOfPayEntity();
        entity1.setId(1L);
        entity1.setName("name1");
        entity1.setDescription("description1");

        MethodOfPayEntity entity2 = new MethodOfPayEntity();
        entity2.setId(2L);
        entity2.setName("name2");
        entity2.setDescription("description2");

        List<MethodOfPayEntity> list = Arrays.asList(entity1,entity2);

        return list;
    }

    public static List<MethodOfPayEntity> listMethodOfPayFlatten(){
        MethodOfPayEntity entity1 = new MethodOfPayEntity();
        entity1.setId(1L);
        entity1.setName("name1");
        entity1.setDescription(null);

        MethodOfPayEntity entity2 = new MethodOfPayEntity();
        entity2.setId(2L);
        entity2.setName("name2");
        entity2.setDescription(null);

        List<MethodOfPayEntity> list = Arrays.asList(entity1,entity2);

        return list;
    }

    
}
