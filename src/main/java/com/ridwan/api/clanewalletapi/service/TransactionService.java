package com.ridwan.api.clanewalletapi.service;

import com.ridwan.api.clanewalletapi.request.TransferRequest;
import com.ridwan.api.clanewalletapi.response.GenericResponse;
import com.ridwan.api.clanewalletapi.response.TransactionResponse;

import java.util.Optional;

/**
 * @author Ridwan Mustapha
 */
public interface TransactionService {
    Optional<GenericResponse<TransactionResponse>> Transfer(TransferRequest request);
    Optional<GenericResponse<TransactionResponse>> Withdraw(TransferRequest request);
    Optional<GenericResponse<TransactionResponse>> TopUp(TransferRequest request);
//    Optional<GenericResponse<User>> WalletTransactionHistory(String accountNumber, String startDate, String endDate);
}
