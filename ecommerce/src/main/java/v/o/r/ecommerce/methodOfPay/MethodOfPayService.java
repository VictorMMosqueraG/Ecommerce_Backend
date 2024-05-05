package v.o.r.ecommerce.methodOfPay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import v.o.r.ecommerce.common.interfaces.methoOfPay.IMethodOfPayService;
import v.o.r.ecommerce.methodOfPay.dto.CreateMethodOfPay;
import v.o.r.ecommerce.methodOfPay.entities.MethodOfPayEntity;
import v.o.r.ecommerce.methodOfPay.repositories.MethodOfPayRepository;

@Service
public class MethodOfPayService implements IMethodOfPayService {
    
    @Autowired
    private MethodOfPayRepository methodOfPayRepository;

    public MethodOfPayEntity save(CreateMethodOfPay createMethodOfPay){
        MethodOfPayEntity methodOfPay = new MethodOfPayEntity();

        methodOfPay.setName(createMethodOfPay.name);
        methodOfPay.setDescription(createMethodOfPay.description);

        methodOfPayRepository.save(methodOfPay);
        return methodOfPay;
    }
}
