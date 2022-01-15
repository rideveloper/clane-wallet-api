package com.ridwan.api.clanewalletapi.service.impl;

import com.ridwan.api.clanewalletapi.model.User;
import com.ridwan.api.clanewalletapi.model.Wallet;
import com.ridwan.api.clanewalletapi.repository.UserRepo;
import com.ridwan.api.clanewalletapi.request.UserRequest;
import com.ridwan.api.clanewalletapi.response.GenericResponse;
import com.ridwan.api.clanewalletapi.service.UserService;
import com.ridwan.api.clanewalletapi.service.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Ridwan Mustapha
 */
@Service
public class UserServImpl implements UserService {

    private final UserRepo userRepo;
    private final WalletService walletService;

    public UserServImpl(UserRepo userRepo, WalletService walletService) {
        this.userRepo = userRepo;
        this.walletService = walletService;
    }

    @Override
    public Optional<GenericResponse<Wallet>> createUser(UserRequest request) {
        GenericResponse response = new GenericResponse();

        if (isUserExisting(request)) {
            response.setMessage("User already exists");
            response.setStatus(HttpStatus.BAD_REQUEST);
        } else {
            //create user model
            User user = new User();
            user.setFirstName(request.getFirstName());
            user.setMiddleName(request.getMiddleName());
            user.setLastName(request.getLastName());
            user.setEmail(request.getEmail());
            user.setPhoneNumber(request.getPhoneNumber());
            user.setAddress(request.getAddress());
            userRepo.saveAndFlush(user);

            //create wallet with user
            Optional<Wallet> wallet = walletService.createUserWallet(user);

            response.setStatus(HttpStatus.CREATED);
            response.setMessage("User created successfully");
            response.setData(wallet.get());

        }

        return Optional.ofNullable(response);
    }

    @Override
    public Optional<GenericResponse<User>> upgradeAccount(Long userId) {
        return Optional.empty();
    }

    private boolean isUserExisting(UserRequest request) {
        //Checks if user exists by querying unique fields
        if (userRepo.findByPhoneNumberOrEmail(request.getPhoneNumber(),
                request.getEmail()) == null) {
            return true;
        }

        return false;
    }
}
