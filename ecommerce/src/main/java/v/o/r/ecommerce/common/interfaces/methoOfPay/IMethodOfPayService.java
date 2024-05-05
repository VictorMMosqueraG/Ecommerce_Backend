package v.o.r.ecommerce.common.interfaces.methoOfPay;

import v.o.r.ecommerce.methodOfPay.dto.CreateMethodOfPay;
import v.o.r.ecommerce.methodOfPay.entities.MethodOfPayEntity;

public interface IMethodOfPayService {
    public MethodOfPayEntity save(CreateMethodOfPay createMethodOfPay);    
}
