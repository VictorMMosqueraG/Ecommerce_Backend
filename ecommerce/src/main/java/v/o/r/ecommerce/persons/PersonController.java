package v.o.r.ecommerce.persons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import v.o.r.ecommerce.common.interfaces.persons.IPersonController;
import v.o.r.ecommerce.common.service.BaseServiceError;
import v.o.r.ecommerce.persons.dto.CreatePerson;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Validated
@Controller
@RequestMapping("person")
public class PersonController implements IPersonController{

    @Autowired
    private PersonService personService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody CreatePerson createPerson){
        try {
         personService.save(createPerson);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return BaseServiceError.handleException(e);
        }
    }
}
