package com.ridwan.api.clanewalletapi.model;

import com.ridwan.api.clanewalletapi.entity.Auditable;
import com.ridwan.api.clanewalletapi.enums.WalletStatus;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Ridwan Mustapha
 */
@Entity
@Table(name = "wallets")
@Data
@EntityListeners({AuditingEntityListener.class})
public class Wallet extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String accountNumber;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    private BigDecimal balance = BigDecimal.valueOf(0.0);

    @Enumerated(EnumType.STRING)
    private WalletStatus status = WalletStatus.CLOSED;
}
