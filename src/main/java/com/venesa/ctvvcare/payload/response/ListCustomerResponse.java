package com.venesa.ctvvcare.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author thuanlm
 * @created at 11/2/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCustomerResponse {
    private int totalRecords;
    private List<CustomerResponse> customerResponses;
}
