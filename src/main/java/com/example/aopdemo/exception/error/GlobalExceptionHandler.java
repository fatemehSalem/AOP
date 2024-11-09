package com.example.aopdemo.exception.error;

import com.example.aopdemo.exception.GeneralNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    // handleHttpMediaTypeNotSupported : triggers when the JSON is invalid
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
        List<String> details = new ArrayList<String>();
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
        details.add(builder.toString());
        ApiError err = new ApiError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, LocalDateTime.now(), "Invalid JSON", details);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    // handleHttpMessageNotReadable : triggers when the JSON is malformed
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());
        ApiError err = new ApiError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, LocalDateTime.now(), "Malformed JSON request", details);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    // handleMethodArgumentNotValid : triggers when @Valid fails
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<String> details = new ArrayList<String>();
        details = ex.getBindingResult().getFieldErrors().stream().map(error -> error.getObjectName() + " : " + error.getDefaultMessage()).collect(Collectors.toList());
        ApiError err = new ApiError(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now(),
                "Validation Errors", details);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    // handleMissingServletRequestParameter : triggers when there are missing parameters
    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException e) {
        List<String> details = new ArrayList<String>();
        details.add(e.getParameterName() + " parameter is missing");
        ApiError err = new ApiError(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST, LocalDateTime.now(),
                "Missing Parameters", details);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(GeneralNotFoundException.class)
    protected ResponseEntity<Object> handleGeneralNotFoundException(GeneralNotFoundException ex) {
        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());
        ApiError err = new ApiError(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST, LocalDateTime.now(),
                "General Exception", details);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
}