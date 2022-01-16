package com.ridwan.api.clanewalletapi.model;

import com.ridwan.api.clanewalletapi.entity.BaseEntity;
import com.ridwan.api.clanewalletapi.enums.PaymentType;
import com.ridwan.api.clanewalletapi.enums.TransactionType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Ridwan Mustapha
 */
@Entity
@Data
@NoArgsConstructor
//@EntityListeners({AuditingEntityListener.class})
public class Transaction extends BaseEntity {

    @NotBlank(message = "Transaction reference is compulsory")
    private String transactionReference;

    private String narration;

    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private Double amount;

    private String sourceWallet;
    private String destinationWallet;

}
