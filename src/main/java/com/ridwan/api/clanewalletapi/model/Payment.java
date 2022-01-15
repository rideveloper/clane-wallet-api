package com.ridwan.api.clanewalletapi.model;

import com.ridwan.api.clanewalletapi.entity.Auditable;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Ridwan Mustapha
 */
@Entity
@Table(name = "payments")
@Data
@EntityListeners({AuditingEntityListener.class})
public class Payment extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Wallet creditWallet;

    @OneToOne(fetch = FetchType.LAZY)
    private Wallet debitWallet;

    private BigDecimal amount;
}
