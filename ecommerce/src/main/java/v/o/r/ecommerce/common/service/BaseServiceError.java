package v.o.r.ecommerce.common.service;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import v.o.r.ecommerce.common.utils.CodeError;

public class BaseServiceError {

    public static ResponseEntity<?> handleException(Exception e) {

        String errorMessage = e.getMessage();

        if (e instanceof DataIntegrityViolationException) {
         
            String detail = errorMessage.substring(errorMessage.indexOf("Key"), errorMessage.indexOf("]") - 1).trim();

            // NOTE: valid if already exist
            if (errorMessage.contains("already exists")) {
                return ResponseError(detail, 400, CodeError.ALREADY_EXIST);
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error DataIntegrityViolationException:" + detail);

        } else if (e instanceof ConstraintViolationException constraintViolationException) {

            for (ConstraintViolation<?> violation : constraintViolationException.getConstraintViolations()) {
                errorMessage = violation.getMessage();

                // NOTE: validation if provide is not null or empty
                if (errorMessage.contains("cannot be blank") || errorMessage.contains("cannot be null")) {
                    return ResponseError(errorMessage, 400, CodeError.MISSING_FILED);
                }

            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error ConstraintViolationException: " + errorMessage);

        } else if (e instanceof IllegalArgumentException) {

            //NOTE: valid if provide flatten with any param
            if(e.getMessage().contains("flatten")){
                return ResponseError(e.getMessage(),400,CodeError.ERROR_PARAMS);
            }else //NOTE: valid if one param is not null o empty 
            if(e.getMessage().contains("can be null or empty")){
                return ResponseError(e.getMessage(), 400, CodeError.BAD_REQUEST);
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error IllegalArgumentException: " + e.getMessage());

        } else if (e instanceof NoSuchElementException) {

            if(e.getMessage().contains("not found.")){
                return ResponseError(e.getMessage(), 400, CodeError.BAD_REQUEST);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Error NoSuchElementException: " + e.getMessage());

        } else {
            return ResponseError("Unexpected Error", 500, CodeError.INTERNAL_SERVER_ERROR);
        }
    }


    public static ResponseEntity<?> ResponseError(String message,Integer status,CodeError code){
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("message", message);
        errorMap.put("status", status);
        errorMap.put("code", code);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(errorMap);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error converting to JSON: " + ex.getMessage());
        }
    }
}
