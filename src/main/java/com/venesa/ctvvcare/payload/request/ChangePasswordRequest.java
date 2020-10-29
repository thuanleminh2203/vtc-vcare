package com.venesa.ctvvcare.payload.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author thuanlm
 * @created at 10/27/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest implements Serializable {
    private String newPassword;
    private String token;
}
