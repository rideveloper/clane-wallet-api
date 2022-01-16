package com.ridwan.api.clanewalletapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author Ridwan Mustapha
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends RuntimeException {

    private String message;
    private HttpStatus status;
}
