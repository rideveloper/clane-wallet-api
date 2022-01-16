package com.ridwan.api.clanewalletapi.controller;

import com.ridwan.api.clanewalletapi.request.TransactionRequest;
import com.ridwan.api.clanewalletapi.response.GenericResponse;
import com.ridwan.api.clanewalletapi.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author Ridwan Mustapha
 */
@RestController
@RequestMapping(path = {"api/v1/transactions"}, produces = APPLICATION_JSON_VALUE)
@Api(tags = {"Transaction"}, value = "api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(path = "/transfer", consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Transfer from wallet to wallet")
    public ResponseEntity<GenericResponse> transfer(@Valid @RequestBody TransactionRequest request) {
        return ResponseEntity.ok(transactionService.Transfer(request));
    }

    @PostMapping(path = "/withdraw", consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Withdraw from Wallet")
    public ResponseEntity<GenericResponse> withdraw(@Valid @RequestBody TransactionRequest request) {
        return ResponseEntity.ok(transactionService.Withdraw(request));
    }

    @PostMapping(path = "/topUp", consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Top Up Wallet")
    public ResponseEntity<GenericResponse> topUp(@RequestBody @Valid TransactionRequest request) {
        return ResponseEntity.ok(transactionService.TopUp(request));
    }

}
