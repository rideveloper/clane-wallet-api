package com.ridwan.api.clanewalletapi.model;

import com.ridwan.api.clanewalletapi.entity.Auditable;
import com.ridwan.api.clanewalletapi.entity.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "user")
    private Wallet wallet;

}
