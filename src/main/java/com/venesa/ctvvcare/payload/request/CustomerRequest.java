package com.venesa.ctvvcare.payload.request;

import com.venesa.ctvvcare.utils.ConstUtils;
import com.venesa.ctvvcare.utils.ValidatorUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author thuanlm
 * @created at 10/21/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest implements Serializable, Validator {
//    private Long customerId;
    private String introductionCode;
    private String phoneNumber;
    private String address;
    private String identifyCard;
    private String email;
    private String password;
    private String bankName;
    private String bankAccountNumber;
    private String bankAccountName;
    private String customerName;
    private Date createdDate;
    private Date updatedDate;
    private String createdBy;
    private String updatedBy;
    private String introduceCustomer;
    private String introduceCustomerCode;

//    private boolean isActive = true;

    @Override
    public boolean supports(Class<?> aClass) {
        return CustomerRequest.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CustomerRequest customerRequest = (CustomerRequest) o;
        ValidatorUtils.checkLength(customerRequest.getPhoneNumber(), errors, 20, 10,"mobile");
        ValidatorUtils.checkRegex(customerRequest.getPhoneNumber(), errors, "mobile", ConstUtils.REGEX_NUMBER_PHONE);

        ValidatorUtils.checkNullOrEmpty(customerRequest.getIdentifyCard(), errors, "identifyCard");
        ValidatorUtils.checkRegex(customerRequest.getIdentifyCard(), errors, "identifyCard", ConstUtils.REGEX_IDENTIFY_CODE);

        ValidatorUtils.checkRegex(customerRequest.getEmail(), errors, "email", ConstUtils.REGEX_EMAIL);

        ValidatorUtils.checkLength(customerRequest.getPassword(), errors, 50,8,"identifyCard");

        ValidatorUtils.checkNullOrEmpty(customerRequest.getBankName(), errors, "bankName");

        ValidatorUtils.checkNullOrEmpty(customerRequest.getBankAccountNumber(), errors, "bankAccountNumber");

        ValidatorUtils.checkNullOrEmpty(customerRequest.getBankAccountName(), errors, "bankAccountName");

        ValidatorUtils.checkNullOrEmpty(customerRequest.getCustomerName(),errors,"customerName");
        ValidatorUtils.checkLength(customerRequest.getCustomerName(),errors,255,1,"customerName");

    }
}
