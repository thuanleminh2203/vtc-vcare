package com.venesa.ctvvcare.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.venesa.ctvvcare.utils.ConstUtils;
import com.venesa.ctvvcare.utils.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) {
        ResponseData<Object> responseData = new ResponseData<>(ConstUtils.ERROR, HttpStatus.FORBIDDEN.getReasonPhrase(), null);
        try {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
            response.getWriter().write(new ObjectMapper().writeValueAsString(responseData));

        } catch (Exception e) {
            System.out.println("======errr======" + e.getMessage());
        }
    }

}
