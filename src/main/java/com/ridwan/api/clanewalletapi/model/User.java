package com.ridwan.api.clanewalletapi.model;

import com.ridwan.api.clanewalletapi.entity.Auditable;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @author Ridwan Mustapha
 */
@Entity
@Table(name = "User")
@Data
@EntityListeners({AuditingEntityListener.class})
public class User extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private Long userId;

    @NotEmpty(message = "First Name is compulsory")
    private String firstName;

    private String middleName;

    @NotEmpty(message = "Last Name is compulsory")
    private String lastName;

    @Email
    @NotEmpty(message = "Email is compulsory")
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "BVN is compulsory")
    @Column(unique = true)
    private String bvn;

    @NotEmpty(message = "Phone Number is compulsory")
    @Column(unique = true)
    private String phoneNumber;

    @NotEmpty(message = "Address is compulsory")
    private String address;
}
