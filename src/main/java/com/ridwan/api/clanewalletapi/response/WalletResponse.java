package com.ridwan.api.clanewalletapi.response;

import com.ridwan.api.clanewalletapi.enums.WalletStatus;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Ridwan Mustapha
 */
@Data
public class WalletResponse {
    private String accountNumber;
    private BigDecimal balance;
    private WalletStatus walletStatus;
}
