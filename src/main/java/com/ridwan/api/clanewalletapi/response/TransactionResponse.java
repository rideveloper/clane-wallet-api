package com.ridwan.api.clanewalletapi.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ridwan.api.clanewalletapi.enums.TransactionType;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Ridwan Mustapha
 */
@Data
public class TransactionResponse {
    private String transactionReference;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String narration;

    private BigDecimal amount;

    private String sourceWallet;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String destinationWallet;

    private TransactionType transactionType;
}
