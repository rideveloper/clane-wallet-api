package com.ridwan.api.clanewalletapi.response;

import com.ridwan.api.clanewalletapi.enums.KycLevel;
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
    private KycLevel kycLevel;
    private WalletResponse wallet;

}
