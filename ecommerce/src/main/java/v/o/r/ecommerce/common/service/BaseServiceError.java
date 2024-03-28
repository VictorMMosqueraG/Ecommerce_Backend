package v.o.r.ecommerce.common.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

public class BaseServiceError{
    
    public static ResponseEntity<String> handleException(Exception e) {
        if (e instanceof DataIntegrityViolationException) {
            String errorMessage = e.getMessage();
            String detail = errorMessage.substring(errorMessage.indexOf("Detail:"), errorMessage.indexOf("]") + 1).trim();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(detail);
        } else if (e instanceof ConstraintViolationException) {
            ConstraintViolationException constraintViolationException = (ConstraintViolationException) e;
            String errorMessage = "";
            for (ConstraintViolation<?> violation : constraintViolationException.getConstraintViolations()) {
                errorMessage = violation.getMessage();
                break;
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error saving user: " + errorMessage);

        } else {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error: " + e);
        }
    }
}
