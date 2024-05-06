package v.o.r.ecommerce.common.interfaces.methodOfPay;

import java.util.List;
import java.util.Map;

import v.o.r.ecommerce.methodOfPay.dto.CreateMethodOfPay;
import v.o.r.ecommerce.methodOfPay.dto.PaginationMethodOfPayDto;
import v.o.r.ecommerce.methodOfPay.entities.MethodOfPayEntity;

public interface IMethodOfPayService {
    public MethodOfPayEntity save(CreateMethodOfPay createMethodOfPay);    
    public List<Map<String,Object>> find(PaginationMethodOfPayDto paginationMethodOfPayDto);
}
