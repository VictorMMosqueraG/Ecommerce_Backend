package v.o.r.ecommerce.common.interfaces.methoOfPay;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import v.o.r.ecommerce.methodOfPay.dto.CreateMethodOfPay;

public interface IMethodOfPayController {
    public ResponseEntity<?> save(@RequestBody CreateMethodOfPay createMethodOfPay);
}
