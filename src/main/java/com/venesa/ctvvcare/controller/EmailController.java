package com.venesa.ctvvcare.controller;


import com.venesa.ctvvcare.payload.request.ChangePasswordRequest;
import com.venesa.ctvvcare.payload.request.EmailRequest;
import com.venesa.ctvvcare.service.CustomerService;
import com.venesa.ctvvcare.service.EmailService;
import com.venesa.ctvvcare.service.UserService;
import com.venesa.ctvvcare.utils.ConstUtils;
import com.venesa.ctvvcare.utils.ResponseData;
import com.venesa.ctvvcare.utils.WapperDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
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
        try {
            emailService.resetPassword(rq);
            responseEntity = WapperDataResponse.sucsses(new ResponseData<>(ConstUtils.SUCCSESS, "", null));
        } catch (Exception e) {
            responseEntity = WapperDataResponse.err(new ResponseData<>(1, e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest rq) {
        ResponseEntity<?> responseEntity;
        try {
            emailService.changePassword(rq);
            responseEntity = WapperDataResponse.sucsses(new ResponseData<>(ConstUtils.SUCCSESS, "", null));

        } catch (Exception e) {
            responseEntity = WapperDataResponse.err(new ResponseData<>(1, e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

}
