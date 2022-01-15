package com.ridwan.api.clanewalletapi.model;

import com.ridwan.api.clanewalletapi.enums.WalletStatus;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Ridwan Mustapha
 */
@Entity
@Table(name = "Wallet")
@Data
@EntityListeners({AuditingEntityListener.class})
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "wallet_id")
    private Long walletId;

    @Column(unique = true)
    private String walletAccountNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    private BigDecimal balance = BigDecimal.valueOf(0.0);

    @Enumerated(EnumType.STRING)
    private WalletStatus status = WalletStatus.OPEN;
}
