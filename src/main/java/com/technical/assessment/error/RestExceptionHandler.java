package com.technical.assessment.error;

import com.technical.assessment.utils.TextMessages;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<Object> noSuchElementException(NoSuchElementException ex, WebRequest request) {

        CustomRestExceptionHandler errors = new CustomRestExceptionHandler();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(TextMessages.REQUEST_DATA_NOT_AVAILABLE);

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }
}