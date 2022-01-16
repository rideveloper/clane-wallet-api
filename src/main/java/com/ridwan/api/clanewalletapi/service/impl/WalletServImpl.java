package com.ridwan.api.clanewalletapi.service.impl;

import com.ridwan.api.clanewalletapi.enums.Status;
import com.ridwan.api.clanewalletapi.enums.WalletStatus;
import com.ridwan.api.clanewalletapi.exception.CustomException;
import com.ridwan.api.clanewalletapi.model.User;
import com.ridwan.api.clanewalletapi.model.Wallet;
import com.ridwan.api.clanewalletapi.repository.WalletRepo;
import com.ridwan.api.clanewalletapi.response.GenericResponse;
import com.ridwan.api.clanewalletapi.service.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Ridwan Mustapha
 */
@Service
public class WalletServImpl implements WalletService {

    private final WalletRepo walletRepo;

    public WalletServImpl(WalletRepo walletRepo) {
        this.walletRepo = walletRepo;
    }

    @Override
    public Optional<Wallet> createUserWallet(User user) {
        Wallet wallet = new Wallet();
        wallet.setAccountNumber(generateAccountNumber(user.getFirstName()));
        wallet.setWalletStatus(WalletStatus.OPEN);
        walletRepo.saveAndFlush(wallet);

        return Optional.of(wallet);
    }

    @Override
    public GenericResponse findUserWallet(String accountNumber) {
        Wallet wallet = walletRepo.findWalletByAccountNumber(accountNumber);

        if (wallet == null)
            throw new CustomException("User Not Found", HttpStatus.NOT_FOUND, Status.FAILED);

        return GenericResponse.builder()
                .status(Status.SUCCESS)
                .message("User Found Successfully")
                .data(wallet).build();
    }

    @Override
    public GenericResponse updateWalletStatus(Long walletId, WalletStatus status) {

        Optional<Wallet> wallet = walletRepo.findById(walletId);

        if (wallet.isEmpty())
            throw new CustomException("Wallet Not Found", HttpStatus.NOT_FOUND, Status.FAILED);

        Wallet theWallet = wallet.get();
        theWallet.setWalletStatus(status);
        walletRepo.saveAndFlush(theWallet);

        return GenericResponse.builder()
                .status(Status.SUCCESS)
                .message("Wallet Status update successful")
                .data(theWallet).build();

    }

    private String generateAccountNumber(String firstName) {
        String randomUUIDString = UUID.randomUUID().toString();
        return firstName + randomUUIDString;
    }
}
