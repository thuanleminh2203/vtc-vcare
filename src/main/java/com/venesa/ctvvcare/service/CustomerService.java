package com.venesa.ctvvcare.service;

import com.venesa.ctvvcare.payload.request.CustomerRequest;
import com.venesa.ctvvcare.payload.response.CustomerResponse;

import java.util.List;

/**
 * @author thuanlm
 * @created at 10/21/2020
 */
public interface CustomerService {
    void save(CustomerRequest request) throws Exception;
    Integer getIntroduceCodeMax() throws Exception;
    Long getIdCustomerIntroduce(String introduceCode) throws Exception;
    String getIntroduceCodeByUsername(String username) throws Exception;
    List<CustomerResponse> findAll();
    List<CustomerResponse> listCustomerByIntroduceCode(String username) throws Exception;

    CustomerResponse myInfoCustomer(String username);
}
