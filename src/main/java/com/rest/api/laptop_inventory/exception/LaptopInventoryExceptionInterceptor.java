package com.rest.api.laptop_inventory.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class LaptopInventoryExceptionInterceptor extends ResponseEntityExceptionHandler {

    public static final String MESSAGE = "message";
    public static final String TIMESTAMP = "timestamp";

    public String exceptionMessage;

    @ExceptionHandler(value = {LaptopInventoryException.class, BadCredentialsException.class})
    public final ResponseEntity  handleResourceNotFoundError(RuntimeException ex) {

        exceptionMessage = ex.getMessage();

        if(ex instanceof BadCredentialsException ){
            exceptionMessage = "Invalid user name and/or password";
        }

        Map<String, Object> errorMap = new LinkedHashMap() {{
            put(MESSAGE, exceptionMessage);
            put(TIMESTAMP, Instant.now());
        }};

        log.error(ex.getMessage());

        return new ResponseEntity(errorMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class})
    public final ResponseEntity  handleAllException(Exception ex) {
        Map<String, Object> errorMap = new LinkedHashMap() {{
            put("message", "Oops somthing goes wrong");
            put("timestamp", Instant.now());
        }};
        ex.printStackTrace();
        return new ResponseEntity(errorMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
