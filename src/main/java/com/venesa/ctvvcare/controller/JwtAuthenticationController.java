package com.venesa.ctvvcare.controller;

import com.venesa.ctvvcare.config.JwtTokenUtil;
import com.venesa.ctvvcare.dto.JwtRequest;
import com.venesa.ctvvcare.dto.JwtResponse;
import com.venesa.ctvvcare.service.JwtUserDetailsService;
import com.venesa.ctvvcare.utils.ConstUtils;
import com.venesa.ctvvcare.utils.ResponseData;
import com.venesa.ctvvcare.utils.WapperDataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationController.class);
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
//    @Autowired
//    private UserDetailsService userDetailService;
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    @Value("${jwt.timetoken}")
    private String jwt_token_validity;

    @PostMapping("/authenticate")
    public ResponseEntity<?> generateAuthenticationToken(@RequestBody JwtRequest rq) {
        ResponseEntity<?> responseEntity;
        try {
            authenticate(rq.getUsername(), rq.getPassword());
            final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(rq.getUsername());
//            userDetails.getAuthorities()
//            Date date = jwtUserDetailsService.getTimeToken(rq.getUsername());
//            if (date != null && date.after((new Date()))) {
//                responseEntity = WapperDataResponse.err(new ResponseData<>(ConstUtils.ERROR, "Tai khoan dang duoc dang nhap", null), HttpStatus.BAD_REQUEST);
//            } else {
//                long timeToken = new Date().getTime() + Long.parseLong(jwt_token_validity) * 1000;
//                jwtUserDetailsService.updateTimeTokenByUsername(rq.getUsername(), new Date(timeToken));
                final String token = jwtTokenUtil.generateToken(userDetails);
                final Collection<? extends GrantedAuthority> role = userDetails.getAuthorities();
                responseEntity = WapperDataResponse.sucsses(new ResponseData<>(ConstUtils.SUCCSESS, "", new JwtResponse(token,role)));
//            }
        } catch (Exception e) {
            responseEntity = WapperDataResponse.err(new ResponseData<>(ConstUtils.ERROR, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

//    @PostMapping("/authenticate/signout")
//    public ResponseEntity<?> logoutUser(Principal principal) {
//        ResponseEntity<?> responseEntity;
//        try {
////            Date date = jwtUserDetailsService.getTimeToken(principal.getName());
////            if (date != null) {
//                jwtUserDetailsService.updateTimeTokenByUsername(principal.getName(), null);
//                responseEntity = WapperDataResponse.sucsses(new ResponseData<>(ConstUtils.SUCCSESS, "", null));
////            } else {
////                responseEntity = WapperDataResponse.err(new ResponseData<>(ConstUtils.ERROR, ConstUtils.ERR_BUSINESS, null), HttpStatus.BAD_REQUEST);
////            }
//
//
//        } catch (Exception e) {
//            log.info("===== err ==" + e.getMessage());
//            responseEntity = WapperDataResponse.err(new ResponseData<>(ConstUtils.ERROR, e.getMessage(), null), HttpStatus.BAD_REQUEST);
//        }
//
//        return responseEntity;
//    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS" + e.getMessage(), e);
        }catch (Exception e){
            log.info("====errrrrrrrr==== " + e.getMessage());
        }
    }
}
