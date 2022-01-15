package com.ridwan.api.clanewalletapi.model;

import com.ridwan.api.clanewalletapi.enums.WalletStatus;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author Ridwan Mustapha
 */
@Entity
@Table(name = "Transaction")
@Data
@EntityListeners({AuditingEntityListener.class})
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "transaction_id", updatable = false)
    private Long transactionId;

    @NotBlank(message = "Transaction reference is compulsory")
    private String transactionReference;

    private String narration;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    private WalletStatus status = WalletStatus.OPEN;
}
