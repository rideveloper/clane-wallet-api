package com.ridwan.api.clanewalletapi.service;

import com.ridwan.api.clanewalletapi.model.User;
import com.ridwan.api.clanewalletapi.model.Wallet;
import com.ridwan.api.clanewalletapi.request.UserRequest;
import com.ridwan.api.clanewalletapi.request.upgradeUserRequest;
import com.ridwan.api.clanewalletapi.response.GenericResponse;
import com.ridwan.api.clanewalletapi.response.UserResponse;

import java.util.Optional;

/**
 * @author Ridwan Mustapha
 */
public interface UserService {
    GenericResponse createUser(UserRequest request);
    GenericResponse updateUser(Long id, UserRequest request);
    GenericResponse upgradeAccount(Long userId, upgradeUserRequest request);
}
