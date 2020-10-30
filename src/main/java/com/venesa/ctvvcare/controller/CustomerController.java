package com.venesa.ctvvcare.controller;

import com.venesa.ctvvcare.component.WrapperResponseData;
import com.venesa.ctvvcare.payload.request.CustomerRequest;
import com.venesa.ctvvcare.payload.response.CustomerRespone;
import com.venesa.ctvvcare.service.CustomerService;
import com.venesa.ctvvcare.service.ExcelExporterService;
import com.venesa.ctvvcare.utils.ConstUtils;
import com.venesa.ctvvcare.utils.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author thuanlm
 * @created at 10/21/2020
 */
//@CrossOrigin
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
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) authentication.getPrincipal();
            var role = user.getAuthorities();
            List<CustomerRespone> list = role.isEmpty() ?  customerService.listCustomerByIntroduceCode(principal.getName()) :  customerService.findAll();
            System.out.println("===== request=== " + principal.getName());
            log.info("=========End create Customer ==========");
            return wrapperResponse.success(new ResponseData<>(ConstUtils.SUCCSESS, ConstUtils.SUCCSESS_MESS,list));

        } catch (Exception e) {
            log.info("=========Err create Customer ==========");
            return wrapperResponse.error(new ResponseData<>(ConstUtils.ERROR, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/download")
    @PreAuthorize("hasAnyRole('ROLE_CTV')")
    public void exportExcel(HttpServletResponse response, Principal principal, HttpServletRequest req) {
        log.info("=========Start export excel Customer ==========");
        try {
            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=customers_" + currentDateTime + ".xlsx";
            response.setHeader(headerKey, headerValue);
            List<CustomerRespone> list = customerService.findAll();
            ExcelExporterService excelExporter = new ExcelExporterService(list);

            excelExporter.export(response);
//            response.sendRedirect(req.getContextPath() + "/login");
            System.out.println("===rendridirect=====" + req.getContextPath());
            log.info("=========End export excel Customer ==========");

        } catch (Exception e) {
            log.info("=========Err export excel Customer ==========");
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
            return wrapperResponse.success(new ResponseData<>(ConstUtils.SUCCSESS, ConstUtils.SUCCSESS_MESS, customerService.myInfoCustomer(principal.getName())));

        } catch (Exception e) {
            log.info("=========Err create Customer ==========");
            return wrapperResponse.error(new ResponseData<>(ConstUtils.ERROR, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
