package com.venesa.ctvvcare.controller;


import com.venesa.ctvvcare.dto.UserDTO;
import com.venesa.ctvvcare.dto.UserDetailDTO;
import com.venesa.ctvvcare.service.JwtUserDetailsService;
import com.venesa.ctvvcare.service.UserService;
import com.venesa.ctvvcare.utils.ConstUtils;
import com.venesa.ctvvcare.utils.ResponseData;
import com.venesa.ctvvcare.utils.WapperDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    @Autowired
    private UserService userService;

//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody UserDTO user) {
//        ResponseEntity<?> responseEntity;
//        try {
//            jwtUserDetailsService.save(user);
//            responseEntity = WapperDataResponse.sucsses(new ResponseData(ConstUtils.SUCCSESS, "", user));
//
//        } catch (Exception e) {
//            responseEntity = WapperDataResponse.err(new ResponseData(ConstUtils.ERROR, e.getMessage(), e.getMessage()), HttpStatus.BAD_REQUEST);
//        }
//        return responseEntity;
//    }

//    @GetMapping("get-username")
//    public ResponseEntity<?> getUserName(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
//                                         @RequestParam(name = "size", required = false, defaultValue = "5") int size,
//                                         @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
//                                         @RequestParam(name = "sortby", required = false, defaultValue = "id") String... sortby) {
//        ResponseEntity<?> responseEntity;
//        try {
//            Sort sortTable = sort.equals("ASC") ? Sort.by(sortby).ascending() : Sort.by(sortby).descending();
//            Page<UserDetailDTO> lst = userService.getUsername(PageRequest.of(page, size, sortTable));
//            responseEntity = WapperDataResponse.sucsses(new ResponseData(null, ConstUtils.SUSSCESS, lst.getContent()));
//
//
//        } catch (Exception e) {
//            responseEntity = WapperDataResponse.err(new ResponseData(null, ConstUtils.ERR, e.getMessage()), HttpStatus.BAD_REQUEST);
//        }
//        return responseEntity;
//    }
//
//    @GetMapping("/find-all")
//    public ResponseEntity<?> findAll() {
//        ResponseEntity<?> responseEntity;
//        try {
//            responseEntity = WapperDataResponse.sucsses(new ResponseData(null, ConstUtils.SUSSCESS, userService.findAll()));
//        } catch (Exception e) {
//            responseEntity = WapperDataResponse.err(new ResponseData(null, ConstUtils.ERR, e.getMessage()), HttpStatus.BAD_REQUEST);
//        }
//        return responseEntity;
//    }
}
