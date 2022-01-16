package com.ridwan.api.clanewalletapi.service.impl;

import com.ridwan.api.clanewalletapi.model.User;
import com.ridwan.api.clanewalletapi.model.Wallet;
import com.ridwan.api.clanewalletapi.repository.UserRepo;
import com.ridwan.api.clanewalletapi.request.UserRequest;
import com.ridwan.api.clanewalletapi.response.GenericResponse;
import com.ridwan.api.clanewalletapi.response.UserResponse;
import com.ridwan.api.clanewalletapi.response.WalletResponse;
import com.ridwan.api.clanewalletapi.service.UserService;
import com.ridwan.api.clanewalletapi.service.WalletService;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;

    public UserServImpl(UserRepo userRepo, WalletService walletService, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.walletService = walletService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<GenericResponse<UserResponse>> createUser(UserRequest request) {
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

            //create wallet with user
            Optional<Wallet> wallet = walletService.createUserWallet(user);

            wallet.ifPresent(user::setWallet);

            userRepo.saveAndFlush(user);

            response.setStatus(HttpStatus.CREATED);
            response.setMessage("User created successfully");
            response.setData(mapUserResponse(user));

        }

        return Optional.of(response);
    }

    @Override
    public Optional<GenericResponse<User>> upgradeAccount(Long userId) {
        return Optional.empty();
    }

    private boolean isUserExisting(UserRequest request) {
        //Checks if user exists by querying unique fields
        return userRepo.findByPhoneNumberOrEmail(request.getPhoneNumber(),
                request.getEmail()) != null;
    }

    private UserResponse mapUserResponse(User user) {
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        WalletResponse walletResponse = modelMapper.map(user.getWallet(), WalletResponse.class);

        userResponse.setWallet(walletResponse);

        return userResponse;
    }
}
