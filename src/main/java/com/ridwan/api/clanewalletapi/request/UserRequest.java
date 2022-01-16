package com.ridwan.api.clanewalletapi.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @author Ridwan Mustapha
 */
@Data
public class UserRequest {

    @NotEmpty(message = "First Name is compulsory")
    private String firstName;

    private String middleName;

    @NotEmpty(message = "Last Name is compulsory")
    private String lastName;

    @Email
    @NotEmpty(message = "Email is compulsory")
    private String email;

    @NotEmpty(message = "Phone Number is compulsory")
    private String phoneNumber;

    @NotEmpty(message = "Address is compulsory")
    private String address;
}
