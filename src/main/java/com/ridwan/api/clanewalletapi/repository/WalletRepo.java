package com.ridwan.api.clanewalletapi.repository;

import com.ridwan.api.clanewalletapi.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ridwan Mustapha
 */
@Repository
public interface WalletRepo extends JpaRepository<Wallet, Long> {
    Wallet findByAccountNumber(String accountNumber);
}
