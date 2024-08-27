package com.cgi.fsdc.advice;

import com.cgi.fsdc.model.ErrorResponse;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class ValidationAspect {

    private static final Logger logger = LoggerFactory.getLogger(ValidationAspect.class);

    @Pointcut("within(com.cgi.fsdc.controller..*)")
    public void controllerLayerPointcut() {
    }

       @AfterThrowing(pointcut = "controllerLayerPointcut()", throwing = "ex")
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> validationErrors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            validationErrors.put(error.getField(), error.getDefaultMessage());
        }

        logger.error("Validation errors occurred: {}", validationErrors);

        ErrorResponse errorResponse = new ErrorResponse(
                "Validation failed",
                validationErrors.toString(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
