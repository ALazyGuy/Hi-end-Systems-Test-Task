package com.ltp.hiendsystemstesttask.exception;

import com.ltp.hiendsystemstesttask.model.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity validationException(final MethodArgumentNotValidException e) {
        final List<String> errors = new LinkedList<>();
        e.getBindingResult().getFieldErrors()
                .forEach(err -> errors.add(err.getDefaultMessage()));
        final ErrorResponse response = new ErrorResponse(errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
