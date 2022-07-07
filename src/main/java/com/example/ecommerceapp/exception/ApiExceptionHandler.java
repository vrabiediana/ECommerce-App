package com.example.ecommerceapp.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handle(ConstraintViolationException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            ErrorItem errorItem = new ErrorItem();
            errorItem.setCode(violation.getMessageTemplate());
            errorItem.setMessage(violation.getMessage());
            errorResponse.addError(errorItem);
        }
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorItem> handle(ResourceNotFoundException e) {
        ErrorItem errorItem = new ErrorItem();
        errorItem.setMessage(e.getMessage());
        return new ResponseEntity<>(errorItem, HttpStatus.NOT_FOUND);
    }


    @Getter
    @Setter
    private static class ErrorResponse {
        private List<ErrorItem> errorItems = new ArrayList<>();
        public void addError(ErrorItem item) {
            this.errorItems.add(item);
        }
    }

    @Getter
    @Setter
    private static class ErrorItem {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String code;
        private String message;
    }
}
