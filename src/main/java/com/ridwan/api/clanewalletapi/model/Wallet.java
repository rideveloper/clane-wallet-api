package com.ridwan.api.clanewalletapi.model;

import com.ridwan.api.clanewalletapi.entity.Auditable;
import com.ridwan.api.clanewalletapi.entity.BaseEntity;
import com.ridwan.api.clanewalletapi.enums.WalletStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Ridwan Mustapha
 */
@Entity
@Data
@NoArgsConstructor
//@EntityListeners({AuditingEntityListener.class})
public class Wallet extends BaseEntity {

    @Column(unique = true)
    private String accountNumber;

    private Double balance = 0.0;

    @Enumerated(EnumType.STRING)
    private WalletStatus walletStatus = WalletStatus.CLOSED;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
