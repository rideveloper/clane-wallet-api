package com.ridwan.api.clanewalletapi.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author Ridwan Mustapha
 */
@Data
public class GenericResponse<T> {

    private HttpStatus status;
    private String message;
    private T data;
}
