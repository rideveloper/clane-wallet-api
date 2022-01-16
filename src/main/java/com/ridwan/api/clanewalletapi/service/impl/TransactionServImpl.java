package com.ridwan.api.clanewalletapi.service.impl;

import com.ridwan.api.clanewalletapi.enums.Status;
import com.ridwan.api.clanewalletapi.enums.TransactionType;
import com.ridwan.api.clanewalletapi.enums.TransferMethod;
import com.ridwan.api.clanewalletapi.enums.WalletStatus;
import com.ridwan.api.clanewalletapi.exception.CustomException;
import com.ridwan.api.clanewalletapi.model.Transaction;
import com.ridwan.api.clanewalletapi.model.User;
import com.ridwan.api.clanewalletapi.model.Wallet;
import com.ridwan.api.clanewalletapi.repository.TransactionRepo;
import com.ridwan.api.clanewalletapi.repository.UserRepo;
import com.ridwan.api.clanewalletapi.repository.WalletRepo;
import com.ridwan.api.clanewalletapi.request.TransactionRequest;
import com.ridwan.api.clanewalletapi.response.GenericResponse;
import com.ridwan.api.clanewalletapi.response.TransactionResponse;
import com.ridwan.api.clanewalletapi.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Ridwan Mustapha
 */
@Service
public class TransactionServImpl implements TransactionService {

    private final UserRepo userRepo;
    private final WalletRepo walletRepo;
    private final TransactionRepo transactionRepo;
    private final ModelMapper modelMapper;

    public TransactionServImpl(UserRepo userRepo, WalletRepo walletRepo, TransactionRepo transactionRepo, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.walletRepo = walletRepo;
        this.transactionRepo = transactionRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public GenericResponse Transfer(TransactionRequest request) {

        validateAmount(request.getAmount());
        processTransaction(request, TransactionType.TRANSFER);
        TransactionResponse transactionResponse = logTransaction(request, TransactionType.TRANSFER);

        return GenericResponse.builder()
                .status(Status.SUCCESS)
                .message("Transfer Successful")
                .data(transactionResponse).build();
    }

    @Override
    public GenericResponse Withdraw(TransactionRequest request) {

        validateAmount(request.getAmount());
        processTransaction(request, TransactionType.WITHDRAW);
        TransactionResponse transactionResponse = logTransaction(request, TransactionType.WITHDRAW);

        return GenericResponse.builder()
                .status(Status.SUCCESS)
                .message("Withdraw Successful")
                .data(transactionResponse).build();
    }

    @Override
    public GenericResponse TopUp(TransactionRequest request) {

        validateAmount(request.getAmount());
        processTransaction(request, TransactionType.TOP_UP);
        TransactionResponse transactionResponse = logTransaction(request, TransactionType.TOP_UP);

        return GenericResponse.builder()
                .status(Status.SUCCESS)
                .message("Top Up Successful")
                .data(transactionResponse).build();
    }

    private void checkWalletValidity(Wallet wallet) {
        if (wallet == null) {
            throw new CustomException("Wallet Not Found", HttpStatus.NOT_FOUND, Status.FAILED);
        }

        if (wallet.getWalletStatus().equals(WalletStatus.CLOSED)) {
            throw new CustomException("Wallet Status Is Closed", HttpStatus.BAD_REQUEST, Status.FAILED);
        }
    }

    private void validateAmount(Double amount) {
        if (amount <= 0.0) {
            throw new CustomException("Amount must be greater than 0.0", HttpStatus.BAD_REQUEST, Status.FAILED);
        }
    }

    private void processTransaction(TransactionRequest request, TransactionType type) {
        Wallet sourceWallet;
        Wallet destinationWallet = null;
        try {
            switch (type) {
                case TOP_UP:
                    sourceWallet = walletRepo.findWalletByAccountNumber(request.getSourceWallet());
                    checkWalletValidity(sourceWallet);
                    sourceWallet.setBalance(sourceWallet.getBalance() + request.getAmount());
                    walletRepo.saveAndFlush(sourceWallet);
                    break;
                case TRANSFER:
                    sourceWallet = walletRepo.findWalletByAccountNumber(request.getSourceWallet());
                    checkWalletValidity(sourceWallet);
                    if (sourceWallet.getBalance() <= request.getAmount()) {
                        throw new CustomException("Insufficient Balance, Please Top Up!", HttpStatus.BAD_REQUEST, Status.FAILED);
                    }

                    if (request.getMethod().equals(TransferMethod.EMAIL)) {
                        User destinationUser = userRepo.findByEmail(request.getDestinationWallet());
                        destinationWallet = destinationUser.getWallet();
                    } else if (request.getMethod().equals(TransferMethod.ACCOUNT_NUMBER)) {
                        destinationWallet = walletRepo.findWalletByAccountNumber(request.getDestinationWallet());
                    }
                    checkWalletValidity(destinationWallet);

                    //debit from source wallet
                    sourceWallet.setBalance(sourceWallet.getBalance() - request.getAmount());
                    walletRepo.saveAndFlush(sourceWallet);

                    //credit destination wallet
                    destinationWallet.setBalance(destinationWallet.getBalance() + request.getAmount());
                    walletRepo.saveAndFlush(destinationWallet);
                    break;
                case WITHDRAW:
                    sourceWallet = walletRepo.findWalletByAccountNumber(request.getSourceWallet());
                    checkWalletValidity(sourceWallet);
                    if (sourceWallet.getBalance() <= request.getAmount()) {
                        throw new CustomException("Insufficient Balance, Please Top Up!", HttpStatus.BAD_REQUEST, Status.FAILED);
                    }
                    sourceWallet.setBalance(sourceWallet.getBalance() - request.getAmount());
                    walletRepo.saveAndFlush(sourceWallet);
                    break;

            }
        } catch (Exception ex) {
            throw new CustomException(ex.getMessage(), HttpStatus.BAD_REQUEST, Status.FAILED);
        }
    }

    private TransactionResponse logTransaction(TransactionRequest request, TransactionType type) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(type);
        transaction.setTransactionReference(generateReference());
        transaction.setAmount(request.getAmount());
        transaction.setDateTime(LocalDateTime.now());

        if (type.equals(TransactionType.TRANSFER))
            transaction.setDestinationWallet(request.getDestinationWallet());
            transaction.setNarration(request.getNarration());

        transaction.setSourceWallet(request.getSourceWallet());

        transactionRepo.saveAndFlush(transaction);

        return modelMapper.map(transaction, TransactionResponse.class);
    }

    private String generateReference() {
        String randomUUIDString = UUID.randomUUID().toString();
        return "REF-"+randomUUIDString;
    }

}
