package com.ridwan.api.clanewalletapi.service;

import com.ridwan.api.clanewalletapi.model.User;
import com.ridwan.api.clanewalletapi.model.Wallet;
import com.ridwan.api.clanewalletapi.request.UserRequest;
import com.ridwan.api.clanewalletapi.response.GenericResponse;
import com.ridwan.api.clanewalletapi.response.UserResponse;

import java.util.Optional;

/**
 * @author Ridwan Mustapha
 */
public interface UserService {
    Optional<GenericResponse<UserResponse>> createUser(UserRequest request);
    Optional<GenericResponse<User>> upgradeAccount(Long userId);
}
