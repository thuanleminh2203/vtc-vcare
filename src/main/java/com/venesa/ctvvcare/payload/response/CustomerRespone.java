package com.venesa.ctvvcare.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author thuanlm
 * @created at 10/22/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRespone implements Serializable {
    private long customerId;
    private String introductionCode;
    private String phoneNumber;
    private String address;
    private String email;
    private String identifyCard;
    private boolean isActive;
    private String bankName;
    private String bankAccountNumber;
    private String bankAccountName;
    private String customerName;

}


