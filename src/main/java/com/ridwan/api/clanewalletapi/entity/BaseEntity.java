package com.ridwan.api.clanewalletapi.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Ridwan Mustapha
 */
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public class BaseEntity extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

}
