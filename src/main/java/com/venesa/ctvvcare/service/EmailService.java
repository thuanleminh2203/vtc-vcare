package com.venesa.ctvvcare.service;

import com.venesa.ctvvcare.dto.EmailDTO;
import com.venesa.ctvvcare.dto.JwtRequest;
import com.venesa.ctvvcare.payload.request.ChangePasswordRequest;
import com.venesa.ctvvcare.payload.request.EmailRequest;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface EmailService {
//	 void sendMail(EmailDTO mail , HttpServletRequest request) throws MessagingException, IOException;

	 void resetPassword(EmailRequest rq) throws Exception;

	 void changePassword(ChangePasswordRequest jwtRequest) throws Exception;

	 boolean checkToken(String token) throws Exception;
}
