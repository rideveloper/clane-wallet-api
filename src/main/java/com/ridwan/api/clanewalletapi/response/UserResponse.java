package com.ridwan.api.clanewalletapi.response;

import lombok.Data;

/**
 * @author Ridwan Mustapha
 */
@Data
public class UserResponse {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private WalletResponse wallet;
}
