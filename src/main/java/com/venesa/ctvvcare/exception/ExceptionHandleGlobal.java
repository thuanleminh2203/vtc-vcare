package com.venesa.ctvvcare.exception;


import com.venesa.ctvvcare.component.WrapperResponseData;
import com.venesa.ctvvcare.utils.ConstUtils;
import com.venesa.ctvvcare.utils.ResponseData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestControllerAdvice
@Slf4j
@AllArgsConstructor
public class ExceptionHandleGlobal {
    private final WrapperResponseData wrapperResponse;

    @ExceptionHandler(NoHandlerFoundException.class)
    public void handleError404(Exception e, HttpServletResponse response) throws Exception {
        response.sendRedirect("http://10.33.60.12/not-found.html");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleErrorRabbitMq(Exception e) {
        log.error("=====err=========" + e.getMessage());
        return wrapperResponse.error(
                new ResponseData<>(ConstUtils.ERROR, e.getMessage(), null),
                HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleErrorNullPointer(Exception e) {
        log.error("=====err=========" + e.getMessage());
        return wrapperResponse.error(
                new ResponseData<>(ConstUtils.ERROR, e.getMessage(), null),
                HttpStatus.NOT_FOUND);
    }


}
