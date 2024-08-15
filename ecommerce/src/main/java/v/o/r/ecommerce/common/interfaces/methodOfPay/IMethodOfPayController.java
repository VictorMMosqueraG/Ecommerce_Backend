package v.o.r.ecommerce.common.interfaces.methodOfPay;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import v.o.r.ecommerce.methodOfPay.dto.CreateMethodOfPayDto;
import v.o.r.ecommerce.methodOfPay.dto.PaginationMethodOfPayDto;

public interface IMethodOfPayController {
    public ResponseEntity<?> save(@RequestBody CreateMethodOfPayDto createMethodOfPay);
    public ResponseEntity<?> find(@ModelAttribute PaginationMethodOfPayDto paginationMethodOfPayDto);
}
