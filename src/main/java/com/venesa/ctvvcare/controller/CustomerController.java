package com.venesa.ctvvcare.controller;

import com.venesa.ctvvcare.component.WrapperResponseData;
import com.venesa.ctvvcare.payload.request.BaseRequest;
import com.venesa.ctvvcare.payload.request.CustomerRequest;
import com.venesa.ctvvcare.payload.response.CustomerResponse;
import com.venesa.ctvvcare.payload.response.ListCustomerResponse;
import com.venesa.ctvvcare.service.CustomerService;
import com.venesa.ctvvcare.utils.ConstUtils;
import com.venesa.ctvvcare.utils.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author thuanlm
 * @created at 10/21/2020
 */
//@CrossOrigin
@RestController
@RequestMapping("/api/v1/ctv-vcare/customer")
@Slf4j
public class CustomerController {
    @Autowired
    private WrapperResponseData wrapperResponse;
    @Autowired
    private CustomerService customerService;

    private Integer introduceCode = 0;

    @PostConstruct
    private void init() {
        try {
            log.info("=========Start get Introduce code max  ==========");
            introduceCode = customerService.getIntroduceCodeMax();
            log.info("=========End get Introduce code max  ==========: " + introduceCode);

        } catch (Exception e) {
            log.error("=========Err get Introduce code max  ==========" + e.getMessage());

        }
    }

    private List<CustomerResponse> filterByCondition(List<CustomerResponse> lst, BaseRequest rq) {
        return lst.stream().skip((rq.getPageIndex() - 1) * rq.getPageSize()).limit(rq.getPageSize()).collect(Collectors.toList());
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CustomerRequest rq, BindingResult result) {
        log.info("=========Start create Customer ==========");
        try {
            rq.validate(rq, result);
            if (result.hasErrors()) {
                log.error("=========Exception update Appointment : validate ==========" + result.getFieldError().getDefaultMessage());
                return wrapperResponse.error(
                        new ResponseData<>(ConstUtils.ERROR, result.getFieldError().getDefaultMessage(), null),
                        HttpStatus.BAD_REQUEST);
            }
            rq.setIntroductionCode((++introduceCode).toString());
            customerService.save(rq);
            log.info("=========End create Customer ==========");
            return wrapperResponse.success(new ResponseData<>(ConstUtils.SUCCSESS, ConstUtils.SUCCSESS_MESS, null));

        } catch (Exception e) {
            log.info("=========Err create Customer ==========" + e.getMessage());
            return wrapperResponse.error(new ResponseData<>(ConstUtils.ERROR, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<?> getCustomersLv2(Principal principal, @RequestBody BaseRequest rq, BindingResult result) {
        log.info("=========Start search Customer ==========");
        try {
            rq.validate(rq, result);
            if (result.hasErrors()) {
                log.error("=========Exception update Appointment : validate ==========" + Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
                return wrapperResponse.error(
                        new ResponseData<>(ConstUtils.ERROR, result.getFieldError().getDefaultMessage(), null),
                        HttpStatus.BAD_REQUEST);
            }
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) authentication.getPrincipal();
            var role = user.getAuthorities();
            var list = role.isEmpty() ? customerService.listCustomerByIntroduceCode(principal.getName()) : customerService.findAll();
            List<CustomerResponse> dataResponse = rq.getPageIndex() == 0 ? list : filterByCondition(list, rq);
            log.info("=========End search Customer ==========");
            return wrapperResponse.success(new ResponseData<>(ConstUtils.SUCCSESS, ConstUtils.SUCCSESS_MESS, new ListCustomerResponse(list.size(), dataResponse)));

        } catch (Exception e) {
            log.info("=========Err search Customer ==========");
            return wrapperResponse.error(new ResponseData<>(ConstUtils.ERROR, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/my-info")
    public ResponseEntity<?> myInfoCustomer(Principal principal) {
        log.info("=========Start create Customer ==========" + principal.getName());
        try {
            var dataResponse = customerService.myInfoCustomer(principal.getName());
            log.info("=========End create Customer ==========");
            return wrapperResponse.success(new ResponseData<>(ConstUtils.SUCCSESS, ConstUtils.SUCCSESS_MESS, dataResponse));

        } catch (Exception e) {
            log.info("=========Err create Customer ==========");
            return wrapperResponse.error(new ResponseData<>(ConstUtils.ERROR, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
