package v.o.r.ecommerce.methodOfPay;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import v.o.r.ecommerce.common.interfaces.methodOfPay.IMethodOfPayService;
import v.o.r.ecommerce.methodOfPay.dto.CreateMethodOfPayDto;
import v.o.r.ecommerce.methodOfPay.dto.PaginationMethodOfPayDto;
import v.o.r.ecommerce.methodOfPay.entities.MethodOfPayEntity;
import v.o.r.ecommerce.methodOfPay.repositories.MethodOfPayRepository;

@Service
public class MethodOfPayService implements IMethodOfPayService {
    
    @Autowired
    private MethodOfPayRepository methodOfPayRepository;

    public MethodOfPayEntity save(CreateMethodOfPayDto createMethodOfPay){
        MethodOfPayEntity methodOfPay = new MethodOfPayEntity();

        methodOfPay.setName(createMethodOfPay.name);
        methodOfPay.setDescription(createMethodOfPay.description);

        methodOfPayRepository.save(methodOfPay);
        return methodOfPay;
    }

    public List<Map<String,Object>> find(PaginationMethodOfPayDto paginationMethodOfPayDto){
        List<MethodOfPayEntity> foundMethodOfPay = methodOfPayRepository.findAll();
        
        //Destructuring paginationMethodOfPay
        boolean flatten = paginationMethodOfPayDto!=null && paginationMethodOfPayDto.isFlatten();
        int limit = paginationMethodOfPayDto!=null && paginationMethodOfPayDto.getLimit()!=0 
            ? paginationMethodOfPayDto.getLimit():50;
        int offset = paginationMethodOfPayDto!=null && paginationMethodOfPayDto.getOffset()!=0 
            ? paginationMethodOfPayDto.getOffset():0;
        String sortOrder =paginationMethodOfPayDto!=null && paginationMethodOfPayDto.getSortOrder()!=null 
            ? paginationMethodOfPayDto.getSortOrder():null;
        String name = paginationMethodOfPayDto!=null && paginationMethodOfPayDto.getName()!=null
            ? paginationMethodOfPayDto.getName():null;

        //Validate if provide flatten
        if(flatten){
            if(flatten && (limit!=50 || offset!=0 || sortOrder!=null || name!=null)){
                throw new IllegalArgumentException(
                    "The PaginationMethodOfPayDto object cannot have other fields besides 'flatten'.");
            }
            
            Map<String,Object> response = new LinkedHashMap<>();
            response.put("context", "MethodOfPay");
            response.put("total",foundMethodOfPay.size());
            response.put("data",foundMethodOfPay.stream()
                .map(methodOfPay->{
                    Map<String,Object> methodOfPayMap = new LinkedHashMap<>();
                    methodOfPayMap.put("id", methodOfPay.getId());
                    methodOfPayMap.put("name", methodOfPay.getName());
                    return methodOfPayMap;
                }).collect(Collectors.toList()));
            return Collections.singletonList(response);
        }

        //Logic for order by ASC or DESC
        Comparator<Map<String,Object>> sort = 
            Comparator.comparing(m -> (String) m.get("name"));

        if (sortOrder != null && sortOrder.equalsIgnoreCase("desc")) {
            sort = sort.reversed();
        }

        //filter base on name if provided
        Predicate<MethodOfPayEntity> nameFilter = methodOfPay -> name == null 
            || methodOfPay.getName().toLowerCase().contains(name.toLowerCase());

        //Create objet and return with the data foundMethodOfPay
        Map<String,Object> response = new LinkedHashMap<>();
        response.put("context", "MethodOfPay");
        response.put("total", foundMethodOfPay.size());
        response.put("data", foundMethodOfPay.stream()
            .filter(nameFilter)
            .map(methodOfPay->{
                Map<String,Object> map = new LinkedHashMap<>();
                map.put("id", methodOfPay.getId());
                map.put("name", methodOfPay.getName());
                map.put("description", methodOfPay.getDescription());

                return map;
            })
            .sorted(sort)
            .limit(limit)
            .skip(offset)
            .collect(Collectors.toList()));

        return Collections.singletonList(response);
    }
}
