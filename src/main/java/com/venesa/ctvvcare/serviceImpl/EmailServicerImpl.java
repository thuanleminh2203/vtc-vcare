package com.venesa.ctvvcare.serviceImpl;

import com.venesa.ctvvcare.dto.JwtRequest;
import com.venesa.ctvvcare.entity.User;
import com.venesa.ctvvcare.payload.request.EmailRequest;
import com.venesa.ctvvcare.payload.response.CustomerRespone;
import com.venesa.ctvvcare.repository.UserRepository;
import com.venesa.ctvvcare.service.CustomerService;
import com.venesa.ctvvcare.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;

@Service
public class EmailServicerImpl implements EmailService {

    @Autowired
    private JavaMailSenderImpl emailSender;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Value("${venesa.mail.account}")
    private String account;

    @Value("${venesa.mail.password}")
    private String password;

    @PostConstruct
    private void init() {
        emailSender.setUsername(account);
        emailSender.setPassword(password);
    }


    @Override
    public void resetPassword(EmailRequest rq) throws Exception {
//        CustomerRespone customerRespone = customerService.myInfoCustomer(rq.getEmail());
        User user = userRepository.findByUsername(rq.getEmail());
        if (user.getUsername() == null) {
            throw new Exception("Not found email " + rq.getEmail());
        }
        String newPassword = bcryptEncoder.encode(new Date().getTime() + "");
        user.setUpdatedBy("system-crm");
        user.setUpdatedDate(new Date());
        user.setPassword(bcryptEncoder.encode(newPassword));
//        userRepository.save()
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Reset password " + "localhost:8088/reset-password/"+rq.getEmail());
        message.setText("Click this url for change password");
        message.setTo(rq.getEmail());
        emailSender.send(message);
    }

    @Override
    public void changePassword(JwtRequest jwtRequest) throws Exception {
        User user = userRepository.findByUsername(jwtRequest.getUsername());
        if(user==null){
            throw new Exception("This mail don't change password");
        }
        String newPassword = bcryptEncoder.encode((jwtRequest.getPassword()));
        user.setUpdatedBy("system-crm");
        user.setUpdatedDate(new Date());
        user.setPassword(newPassword);
        user.setResetPassword(false);
        userRepository.save(user);

    }


}
