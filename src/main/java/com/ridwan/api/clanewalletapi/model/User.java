package com.ridwan.api.clanewalletapi.model;

import com.ridwan.api.clanewalletapi.entity.BaseEntity;
import com.ridwan.api.clanewalletapi.enums.KycLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @author Ridwan Mustapha
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "\"user\"")
//@EntityListeners({AuditingEntityListener.class})
public class User extends BaseEntity {

    @NotEmpty(message = "First Name is compulsory")
    private String firstName;

    private String middleName;

    @NotEmpty(message = "Last Name is compulsory")
    private String lastName;

    @Email
    @NotEmpty(message = "Email is compulsory")
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "Phone Number is compulsory")
    @Column(unique = true)
    private String phoneNumber;

    @NotEmpty(message = "Address is compulsory")
    private String address;

    @OneToOne(fetch = FetchType.LAZY)
    private Wallet wallet;

    @Enumerated(EnumType.STRING)
    @Column(name = "kyc_level")
    private KycLevel kycLevel = KycLevel.TIER_1;

    @Column(name = "selfie_url")
    private String selfieUrl;

    @Column(name = "identification_doc_url")
    private String identificationDocUrl;

}
