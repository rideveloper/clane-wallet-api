package com.ridwan.api.clanewalletapi.controller;

import com.ridwan.api.clanewalletapi.request.UserRequest;
import com.ridwan.api.clanewalletapi.request.upgradeUserRequest;
import com.ridwan.api.clanewalletapi.response.GenericResponse;
import com.ridwan.api.clanewalletapi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author Ridwan Mustapha
 */
@RestController
@RequestMapping(path = {"api/v1/users"}, produces = APPLICATION_JSON_VALUE)
@Api(tags = {"User"}, value = "api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/register", consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Register a user and create wallet")
    public ResponseEntity<GenericResponse> register(@Valid @RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @PutMapping(path = "/{id}/update", consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update User Details")
    public ResponseEntity<GenericResponse> withdraw(@PathVariable("id") Long id, @Valid @RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }

    @PutMapping(path = "/{id}/upgrade", consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Upgrade User KYC Level")
    public ResponseEntity<GenericResponse> upgrade(@PathVariable("id") Long id, @Valid @RequestBody upgradeUserRequest request) {
        return ResponseEntity.ok(userService.upgradeAccount(id, request));
    }

}
