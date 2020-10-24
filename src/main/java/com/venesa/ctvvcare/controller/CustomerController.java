package com.venesa.ctvvcare.controller;

import com.venesa.ctvvcare.component.WrapperResponseData;
import com.venesa.ctvvcare.config.UserPrincipal;
import com.venesa.ctvvcare.entity.User;
import com.venesa.ctvvcare.payload.request.CustomerRequest;
import com.venesa.ctvvcare.service.CustomerService;
import com.venesa.ctvvcare.utils.ConstUtils;
import com.venesa.ctvvcare.utils.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Objects;

/**
 * @author thuanlm
 * @created at 10/21/2020
 */
@CrossOrigin
@RestController
//@RequestMapping("/api/v1/customer")
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

    @PostMapping("/api/v1/customer")
    public ResponseEntity<?> register(@RequestBody CustomerRequest rq, BindingResult result) {
        log.info("=========Start create Customer ==========");
        try {
            rq.validate(rq, result);
            if (result.hasErrors()) {
                log.error("=========Exception update Appointment : validate ==========" + Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
                return wrapperResponse.error(
                        new ResponseData<>(ConstUtils.ERROR, result.getFieldError().getDefaultMessage(), null),
                        HttpStatus.BAD_REQUEST);
            }
            rq.setIntroductionCode((++introduceCode).toString());
            customerService.save(rq);
            log.info("=========End create Customer ==========");
            return wrapperResponse.success(new ResponseData<>(ConstUtils.SUCCSESS, ConstUtils.SUCCSESS_MESS, null));

        } catch (Exception e) {
            log.info("=========Err create Customer ==========");
            return wrapperResponse.error(new ResponseData<>(ConstUtils.ERROR, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getCustomersLv2(Principal principal) {
        log.info("=========Start create Customer ==========");
        try {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            User user = (User) authentication.getPrincipal();
            System.out.println("===== request=== " + principal.getName());
//            customerService.listCustomerByIntroduceCode(principal.getName());
            log.info("=========End create Customer ==========");
            return wrapperResponse.success(new ResponseData<>(ConstUtils.SUCCSESS, ConstUtils.SUCCSESS_MESS,  customerService.listCustomerByIntroduceCode(principal.getName())));

        } catch (Exception e) {
            log.info("=========Err create Customer ==========");
            return wrapperResponse.error(new ResponseData<>(ConstUtils.ERROR, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/v1/customer")
    public ResponseEntity<?> myInfoCustomer(Principal principal) {
        log.info("=========Start create Customer ==========");
        try {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            User user = (User) authentication.getPrincipal();
            System.out.println("===== request=== " + principal.getName());
//            customerService.listCustomerByIntroduceCode(principal.getName());
            log.info("=========End create Customer ==========");
            return wrapperResponse.success(new ResponseData<>(ConstUtils.SUCCSESS, ConstUtils.SUCCSESS_MESS,  customerService.myInfoCustomer(principal.getName())));

        } catch (Exception e) {
            log.info("=========Err create Customer ==========");
            return wrapperResponse.error(new ResponseData<>(ConstUtils.ERROR, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
