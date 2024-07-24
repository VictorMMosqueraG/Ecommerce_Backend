package v.o.r.ecommerce.seed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "seed")
@RequestMapping("seed")
public class SeedController {
    
    @Autowired
    private SeedService seedService;

    @PostMapping
    public ResponseEntity<?> seed(){
        seedService.InitializeData();
        return ResponseEntity.ok("Data initialization successful");
    }
}
