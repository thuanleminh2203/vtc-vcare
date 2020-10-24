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
public class EmailResponse implements Serializable {
    private String url;
}
