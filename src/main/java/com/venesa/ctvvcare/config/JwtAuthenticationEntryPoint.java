package com.venesa.ctvvcare.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.venesa.ctvvcare.utils.ConstUtils;
import com.venesa.ctvvcare.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
	private static final long serialVersionUID = -7858869558953243875L;
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		String expired = (String) request.getAttribute("expried");
		ResponseData responseData = new ResponseData();
		responseData.setErrorMessage("Invalid Login details");
		if (expired != null) {
			request.removeAttribute("expired");
			responseData.setErrorMessage("JWT Token has expired");
		}
		responseData.setErrorCode(ConstUtils.ERROR);
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
		String json = objectMapper.writeValueAsString(responseData);
		response.getWriter().write(json);

	}

}
