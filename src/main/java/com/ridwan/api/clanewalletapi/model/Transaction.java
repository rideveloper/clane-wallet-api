package com.ridwan.api.clanewalletapi.model;

import com.ridwan.api.clanewalletapi.entity.Auditable;
import com.ridwan.api.clanewalletapi.enums.TransactionType;
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
@Table(name = "transactions")
@Data
@EntityListeners({AuditingEntityListener.class})
public class Transaction extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(updatable = false)
    private Long id;

    @NotBlank(message = "Transaction reference is compulsory")
    private String transactionReference;

    private String narration;

    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @OneToOne(fetch = FetchType.LAZY)
    private Payment payment;
}
