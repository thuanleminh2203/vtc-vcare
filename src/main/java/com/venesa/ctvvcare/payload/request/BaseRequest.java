package com.venesa.ctvvcare.payload.request;

import com.venesa.ctvvcare.utils.ValidatorUtils;
import lombok.Data;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.io.Serializable;

/**
 * @author thuanlm
 * @created at 11/2/2020
 */
@Data
public class BaseRequest implements Validator, Serializable {
    private int pageIndex = 1;
    private int pageSize = 5;

    @Override
    public boolean supports(Class<?> aClass) {
        return BaseRequest.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        BaseRequest rq = (BaseRequest) o;
        ValidatorUtils.checkPageable(rq.getPageIndex(), errors,  "pageIndex");
        ValidatorUtils.checkPageable(rq.getPageSize(), errors,  "pageSize");
    }
}
