package com.ridwan.api.clanewalletapi.service;

import com.ridwan.api.clanewalletapi.enums.WalletStatus;
import com.ridwan.api.clanewalletapi.model.User;
import com.ridwan.api.clanewalletapi.model.Wallet;
import com.ridwan.api.clanewalletapi.response.GenericResponse;

import java.util.Optional;

/**
 * @author Ridwan Mustapha
 */
public interface WalletService {
    Optional<Wallet> createUserWallet(User user);
    GenericResponse findUserWallet(String accountNumber);
    GenericResponse updateWalletStatus(Long walletId, WalletStatus status);
    GenericResponse getBalance(String accountNumber);
}
