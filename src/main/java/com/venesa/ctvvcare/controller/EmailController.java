package com.venesa.ctvvcare.controller;


import com.venesa.ctvvcare.payload.request.ChangePasswordRequest;
import com.venesa.ctvvcare.payload.request.EmailRequest;
import com.venesa.ctvvcare.service.CustomerService;
import com.venesa.ctvvcare.service.EmailService;
import com.venesa.ctvvcare.service.UserService;
import com.venesa.ctvvcare.utils.ConstUtils;
import com.venesa.ctvvcare.utils.ResponseData;
import com.venesa.ctvvcare.utils.WapperDataResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api/v1/ctv-vcare/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;


    @PostMapping("/reset-password")
    public ResponseEntity<?> sendMail(@RequestBody EmailRequest rq) {
        ResponseEntity<?> responseEntity;
        log.info("===Start reset password ====");
        try {
            emailService.resetPassword(rq);
            responseEntity = WapperDataResponse.sucsses(new ResponseData<>(ConstUtils.SUCCSESS, "", null));
        } catch (Exception e) {
            log.info("===Err reset password ====" + e.getMessage());
            responseEntity = WapperDataResponse.err(new ResponseData<>(1, e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
        log.info("===End reset password ====");

        return responseEntity;
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest rq) {
        log.info("===Start reset password ====");
        try {
            emailService.changePassword(rq);
            log.info("===End reset password ====");
            return WapperDataResponse.sucsses(new ResponseData<>(ConstUtils.SUCCSESS, "", null));
        } catch (Exception e) {
            log.info("===Err reset password ====");
            return WapperDataResponse.err(new ResponseData<>(1, e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/redirect-page-pwd/{token}")
    public void redirectPageChangePassword(@PathVariable String token, HttpServletResponse response) {
        try {
            log.info("===Start redirect to page change password ====");

            response.sendRedirect("http://10.33.60.12/change-password.html?token=" + token);
        } catch (Exception e) {
            log.error("==== Error when redirect page =====" + e.getMessage());
        }

    }

}
