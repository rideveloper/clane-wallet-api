package com.ridwan.api.clanewalletapi.repository;

import com.ridwan.api.clanewalletapi.model.User;
import com.ridwan.api.clanewalletapi.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ridwan Mustapha
 */
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByPhoneNumberOrEmail(String phoneNumber, String email);
}
