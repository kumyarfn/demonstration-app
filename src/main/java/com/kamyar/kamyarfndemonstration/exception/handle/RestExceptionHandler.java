package com.kamyar.kamyarfndemonstration.exception.handle;

import com.kamyar.kamyarfndemonstration.exception.BaseException;
import com.kamyar.kamyarfndemonstration.dto.response.HttpResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

import static com.kamyar.kamyarfndemonstration.enums.ResultMessage.*;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<HttpResponse> handleGeneralException(Exception e) {
        return ResponseEntity.badRequest().body(HttpResponse.create(EXCEPTION.getCode(), e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<HttpResponse> handleRuntimeException(RuntimeException e) {
        return ResponseEntity.badRequest().body(HttpResponse.create(EXCEPTION.getCode(), e.getMessage()));
    }


    @ExceptionHandler
    public ResponseEntity<HttpResponse> handleBaseException(BaseException e) {
        return ResponseEntity.badRequest().body(HttpResponse.create(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<HttpResponse> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.badRequest().body(HttpResponse.create(AUTHENTICATION_EXCEPTION.getCode(), AUTHENTICATION_EXCEPTION.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.badRequest().body(HttpResponse.create(VALIDATION.getCode(), ex.getBindingResult().getFieldErrors()
                .stream().parallel().map(DefaultMessageSourceResolvable::getDefaultMessage).sorted().collect(Collectors.toList())));
    }

    @Override
    protected ResponseEntity<Object>handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                 HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.badRequest().body(HttpResponse.create(VALIDATION.getCode(), VALIDATION.getMessage()));
    }


    @ExceptionHandler
    public ResponseEntity<HttpResponse> handleLockedException(LockedException e) {
        return new ResponseEntity<>(HttpResponse.create(USER_BANNED.getCode(), USER_BANNED.getMessage()), HttpStatus.FORBIDDEN);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(HttpResponse.create(PAGE_NOT_FOUND.getCode(), PAGE_NOT_FOUND.getMessage()), HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported
            (HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(HttpResponse.create(METHOD_NOT_ALLOWED.getCode(), METHOD_NOT_ALLOWED.getMessage()), HttpStatus.METHOD_NOT_ALLOWED);
    }
}
