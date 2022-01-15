package com.ridwan.api.clanewalletapi.service.impl;

import com.ridwan.api.clanewalletapi.enums.WalletStatus;
import com.ridwan.api.clanewalletapi.model.User;
import com.ridwan.api.clanewalletapi.model.Wallet;
import com.ridwan.api.clanewalletapi.repository.WalletRepo;
import com.ridwan.api.clanewalletapi.response.GenericResponse;
import com.ridwan.api.clanewalletapi.service.WalletService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        wallet.setUser(user);
        wallet.setStatus(WalletStatus.OPEN);
        walletRepo.saveAndFlush(wallet);

        return Optional.of(wallet);
    }

    @Override
    public Optional<GenericResponse<Wallet>> findUserWallet(String accountNumber) {
        GenericResponse response = new GenericResponse();
        response.setStatus(HttpStatus.NOT_FOUND);
        response.setMessage("Wallet cannot be found");

        Wallet wallet = walletRepo.findWalletByAccountNumber(accountNumber);

        if (wallet != null) {
            response.setStatus(HttpStatus.OK);
            response.setMessage("Wallet found successfully");
            response.setData(wallet);
        }

        return Optional.of(response);
    }

    @Override
    public Optional<GenericResponse<Wallet>> updateWalletStatus(Long walletId, WalletStatus status) {
        GenericResponse response = new GenericResponse();
        response.setStatus(HttpStatus.NOT_FOUND);
        response.setMessage("Wallet cannot be found");

        Optional<Wallet> wallet = walletRepo.findById(walletId);
        if (wallet.isPresent()) {
            Wallet theWallet = wallet.get();
            theWallet.setStatus(status);
            walletRepo.saveAndFlush(theWallet);

            response.setStatus(HttpStatus.OK);
            response.setMessage("Wallet Status update successful");
            response.setData(theWallet);
        }

        return Optional.of(response);
    }

    private String generateAccountNumber(String firstName) {
        String randomUUIDString = RandomStringUtils.random(5);
        return firstName + randomUUIDString;
    }
}
