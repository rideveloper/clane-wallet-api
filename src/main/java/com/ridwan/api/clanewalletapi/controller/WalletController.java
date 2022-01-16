package com.ridwan.api.clanewalletapi.controller;

import com.ridwan.api.clanewalletapi.enums.WalletStatus;
import com.ridwan.api.clanewalletapi.request.UserRequest;
import com.ridwan.api.clanewalletapi.response.GenericResponse;
import com.ridwan.api.clanewalletapi.service.WalletService;
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
@RequestMapping(path = {"api/v1/wallet"}, produces = APPLICATION_JSON_VALUE)
@Api(tags = {"Wallet"}, value = "api/v1/wallet")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PutMapping(path = "/find", consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find Wallet")
    public ResponseEntity<GenericResponse> find(@Valid @RequestBody UserRequest request) {
        return ResponseEntity.ok(walletService.findUserWallet(""));
    }

    @PostMapping(path = "/{id}/updateStatus", consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Wallet Status")
    public ResponseEntity<GenericResponse> updateStatus(@PathVariable("id") Long id, @RequestBody @Valid WalletStatus request) {
        return ResponseEntity.ok(walletService.updateWalletStatus(id, request));
    }

}
