package com.ridwan.api.clanewalletapi.response;

import com.ridwan.api.clanewalletapi.enums.Status;
import lombok.Builder;
import lombok.Data;

/**
 * @author Ridwan Mustapha
 */
@Data
@Builder
public class GenericResponse {

    private Status status;
    private String message;
    private Object data;
}
