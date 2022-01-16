package com.ridwan.api.clanewalletapi.request;

import com.ridwan.api.clanewalletapi.enums.TransferMethod;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

/**
 * @author Ridwan Mustapha
 */
@Data
public class TransactionRequest {

    @NotEmpty(message = "Source Wallet is compulsory")
    private String sourceWallet;

    @NotEmpty(message = "Destination Wallet is compulsory")
    private String destinationWallet;

    @NotEmpty(message = "Amount is compulsory")
    private double amount;

    private TransferMethod method;

    private String narration;

}
