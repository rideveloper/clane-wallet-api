package com.ridwan.api.clanewalletapi.service.impl;

import com.ridwan.api.clanewalletapi.enums.KycLevel;
import com.ridwan.api.clanewalletapi.enums.Status;
import com.ridwan.api.clanewalletapi.exception.CustomException;
import com.ridwan.api.clanewalletapi.model.User;
import com.ridwan.api.clanewalletapi.model.Wallet;
import com.ridwan.api.clanewalletapi.repository.UserRepo;
import com.ridwan.api.clanewalletapi.request.UserRequest;
import com.ridwan.api.clanewalletapi.request.upgradeUserRequest;
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
    public GenericResponse createUser(UserRequest request) {
        if (isUserExisting(request))
            throw new CustomException("User Already Exists", HttpStatus.BAD_REQUEST, Status.FAILED);

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

        return GenericResponse.builder()
                .status(Status.SUCCESS)
                .message("User Created Successfully")
                .data(mapUserResponse(user)).build();
    }

    @Override
    public GenericResponse updateUser(Long id, UserRequest request) {
        Optional<User> existingUserOp = userRepo.findById(id);
        if (existingUserOp.isEmpty())
            throw new CustomException("User Not Found", HttpStatus.NOT_FOUND, Status.FAILED);

        User existingUser = existingUserOp.get();
        existingUser.setFirstName(request.getFirstName());
        existingUser.setMiddleName(request.getMiddleName());
        existingUser.setLastName(request.getLastName());
        existingUser.setAddress(request.getAddress());

        userRepo.saveAndFlush(existingUser);

        return GenericResponse.builder()
                .status(Status.SUCCESS)
                .message("User Updated Successfully")
                .data(mapUserResponse(existingUser)).build();
    }

    @Override
    public GenericResponse upgradeAccount(Long userId, upgradeUserRequest request) {
        //get user
        Optional<User> userOpt = userRepo.findById(userId);
        if (userOpt.isEmpty())
            throw new CustomException("User Not Found", HttpStatus.NOT_FOUND, Status.FAILED);

        User user = userOpt.get();

        //check user kycLevel
        switch (user.getKycLevel()) {
            case TIER_1:
                //check if required doc is available
                if (request.getSelfieUrl() == null || request.getSelfieUrl().isEmpty())
                    throw new CustomException("Required document not uploaded", HttpStatus.BAD_REQUEST, Status.FAILED);

                //user is in tier1, upgrade to tier 2
                user.setSelfieUrl(request.getSelfieUrl());
                user.setKycLevel(KycLevel.TIER_2);
                break;
            case TIER_2:
                if (request.getIdentificationDocUrl() == null || request.getIdentificationDocUrl().isEmpty())
                    throw new CustomException("Required document not uploaded", HttpStatus.BAD_REQUEST, Status.FAILED);

                user.setIdentificationDocUrl(request.getSelfieUrl());
                user.setKycLevel(KycLevel.TIER_3);
                break;
            case TIER_3:
                return GenericResponse.builder()
                        .status(Status.SUCCESS)
                        .message("User already in highest Kyc Level")
                        .data(mapUserResponse(user)).build();
        }

        userRepo.saveAndFlush(user);

        return GenericResponse.builder()
                .status(Status.SUCCESS)
                .message("User KYC Level Upgraded Successfully")
                .data(mapUserResponse(user)).build();
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
