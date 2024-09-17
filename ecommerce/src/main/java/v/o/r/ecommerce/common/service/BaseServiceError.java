package v.o.r.ecommerce.common.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import v.o.r.ecommerce.common.utils.CodeError;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class BaseServiceError {

    public static ResponseEntity<?> handleException(Exception e) {
        if (e instanceof DataIntegrityViolationException) {//valid if already exist 
            return handleDataIntegrityViolationException((DataIntegrityViolationException) e);
            
        } else if (e instanceof ConstraintViolationException) {//validate if already exist 
            return handleConstraintViolationException((ConstraintViolationException) e);

        } else if (e instanceof IllegalArgumentException) {//validate if provides flatten and other value
            return handleIllegalArgumentException((IllegalArgumentException) e);

        } else if (e instanceof NoSuchElementException) {//validate if not found element
            return handleNoSuchElementException((NoSuchElementException) e);
            
        } else {//internal server error
            return handleUnexpectedException(e);
        }
    }

    //method for validate if already exist 
    private static ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        String errorMessage = e.getMessage();
        String detail = errorMessage.substring(errorMessage.indexOf("Key"), errorMessage.indexOf("]") - 1).trim();

        if (errorMessage.contains("already exists")) {
            return ResponseError(detail, 400, CodeError.ALREADY_EXIST);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error DataIntegrityViolationException: " + detail);
    }

    //method for validate if is blank or empty
    private static ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e) {
        String errorMessage = "";
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            errorMessage = violation.getMessage();

            if (errorMessage.contains("cannot be blank") || errorMessage.contains("cannot be null")) {
                return ResponseError(errorMessage, 400, CodeError.MISSING_FILED);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error ConstraintViolationException: " + errorMessage);
    }

    //method for validate if provide flatten and other value
    private static ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
        if (e.getMessage().contains("flatten")) {
            return ResponseError(e.getMessage(), 400, CodeError.ERROR_PARAMS);
        } else if (e.getMessage().contains("can be null or empty")) {
            return ResponseError(e.getMessage(), 400, CodeError.BAD_REQUEST);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error IllegalArgumentException: " + e.getMessage());
    }

    //method not found element
    private static ResponseEntity<?> handleNoSuchElementException(NoSuchElementException e) {
        if (e.getMessage().contains("not found.")) {
            return ResponseError(e.getMessage(), 400, CodeError.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Error NoSuchElementException: " + e.getMessage());
    }

    //method internalServerError
    private static ResponseEntity<?> handleUnexpectedException(Exception e) {
        return ResponseError("Unexpected Error", 500, CodeError.INTERNAL_SERVER_ERROR);
    }

    
    //NOTE: this method is for return error with format Json
    public static ResponseEntity<?> ResponseError(String message, Integer status, CodeError code) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("message", message);
        errorMap.put("status", status);
        errorMap.put("code", code);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(errorMap);
            return ResponseEntity.status(status)
                .header("Content-Type", "application/json")
                .body(json);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error converting to JSON: " + ex.getMessage());
        }
    }
}
