package com.ridwan.api.clanewalletapi.exception;

import com.ridwan.api.clanewalletapi.enums.Status;
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
    private HttpStatus httpStatus;
    private Status status;
}
