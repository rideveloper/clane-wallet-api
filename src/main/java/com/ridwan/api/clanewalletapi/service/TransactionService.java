package com.ridwan.api.clanewalletapi.service;

import com.ridwan.api.clanewalletapi.request.TransactionRequest;
import com.ridwan.api.clanewalletapi.response.GenericResponse;
import com.ridwan.api.clanewalletapi.response.TransactionResponse;

import java.util.Optional;

/**
 * @author Ridwan Mustapha
 */
public interface TransactionService {
    GenericResponse Transfer(TransactionRequest request);
    GenericResponse Withdraw(TransactionRequest request);
    GenericResponse TopUp(TransactionRequest request);
//    Optional<GenericResponse<User>> WalletTransactionHistory(String accountNumber, String startDate, String endDate);
}
