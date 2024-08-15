package v.o.r.ecommerce.common.interfaces.methodOfPay;

import java.util.List;
import java.util.Map;

import v.o.r.ecommerce.methodOfPay.dto.CreateMethodOfPayDto;
import v.o.r.ecommerce.methodOfPay.dto.PaginationMethodOfPayDto;
import v.o.r.ecommerce.methodOfPay.entities.MethodOfPayEntity;

public interface IMethodOfPayService {
    public MethodOfPayEntity save(CreateMethodOfPayDto createMethodOfPay);    
    public List<Map<String,Object>> find(PaginationMethodOfPayDto paginationMethodOfPayDto);
}
