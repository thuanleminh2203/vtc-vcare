package com.venesa.ctvvcare.serviceImpl;

import com.venesa.ctvvcare.entity.User;
import com.venesa.ctvvcare.payload.request.ChangePasswordRequest;
import com.venesa.ctvvcare.payload.request.EmailRequest;
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
import java.util.UUID;

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
        emailSender.setUsername("anhku0j97@gmail.com");
        emailSender.setPassword("0915234576");
    }


    @Override
    public void resetPassword(EmailRequest rq) throws Exception {
        User user = userRepository.findByUsername(rq.getEmail());
        if (user == null) {
            throw new Exception("Not found User with email: " + rq.getEmail());
        }
        String newPassword = bcryptEncoder.encode(new Date().getTime() + "");
        user.setUpdatedBy("system-crm");
        user.setUpdatedDate(new Date());
        user.setPassword(bcryptEncoder.encode(newPassword));
        String token = UUID.randomUUID().toString();
        user.setTokenResetPassword(token);
        user.setExpiryDateToken(new Date(System.currentTimeMillis() + 24*60*60*1000));
        userRepository.save(user);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Reset password ");
        message.setText("Click this url for change password: 192.168.20.116:8088/redirect-page-pwd/"+token);
        message.setTo(rq.getEmail());
        emailSender.send(message);
    }

    @Override
    public void changePassword(ChangePasswordRequest rq) throws Exception {
        User user = userRepository.findByToken(rq.getToken());
        if(user==null){
            throw new Exception("This mail don't change password");
        }
        if((new Date().before(user.getExpiryDateToken()))){
            throw new Exception("InvalidToken : Expired");
        }
        String newPassword = bcryptEncoder.encode((rq.getNewPassword()));
        user.setUpdatedBy("user");
        user.setUpdatedDate(new Date());
        user.setPassword(newPassword);
        userRepository.save(user);

    }


}
