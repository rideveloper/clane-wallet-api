package com.ridwan.api.clanewalletapi.repository;

import com.ridwan.api.clanewalletapi.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ridwan Mustapha
 */
@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {

}
