package com.ridwan.api.clanewalletapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Ridwan Mustapha
 */
@AllArgsConstructor
@Getter
public enum KycLevel {
    TIER_1(10000.00), TIER_2(50000.00), TIER_3(100000.00);

    private final Double transactionLimit;

}
