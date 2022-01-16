package com.ridwan.api.clanewalletapi.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * @author Ridwan Mustapha
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ HttpMessageNotReadableException.class })
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException customException) {
        return buildResponseEntity(new ApiError(customException.getHttpStatus(),
                customException.getMessage(), customException));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ MethodArgumentNotValidException.class })
    protected ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,
                ex.getMessage(), ex));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupportedCustom(HttpRequestMethodNotSupportedException ex) {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,
                ex.getMessage(), ex));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ Exception.class })
    protected ResponseEntity<Object> otherAppRelatedExceptions(Exception ex) {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,
                ex.getMessage(), ex));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ ConversionFailedException.class })
    protected ResponseEntity<Object> conversion(Exception ex) {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,
                ex.getMessage(), ex));
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({ BindException.class })
    protected ResponseEntity<Object> bindExceptionHandler(BindException ex) {
        return buildResponseEntity(new ApiError(HttpStatus.NOT_ACCEPTABLE,
                ex.getMessage(), ex));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError error) {
        return new ResponseEntity<>(error, error.getStatus());
    }
}
