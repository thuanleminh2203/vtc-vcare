package com.venesa.ctvvcare.serviceImpl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.venesa.ctvvcare.entity.CustomerEntity;
import com.venesa.ctvvcare.entity.User;
import com.venesa.ctvvcare.payload.request.CustomerRequest;
import com.venesa.ctvvcare.payload.response.CustomerRespone;
import com.venesa.ctvvcare.repository.CustomerRepository;
import com.venesa.ctvvcare.repository.IntroduceRepository;
import com.venesa.ctvvcare.repository.UserRepository;
import com.venesa.ctvvcare.service.CustomerService;
import com.venesa.ctvvcare.service.JwtUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author thuanlm
 * @created at 10/21/2020
 */
@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ObjectMapper mapper;
    private final IntroduceRepository introduceRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder bcryptEncoder;
    private final JwtUserDetailsService jwtUserDetailsService;

    @Override
//    @Transactional(rollbackFor = Exception.class)
    public void save(CustomerRequest request) throws Exception {
        request.setCreatedDate(new Date());
        request.setCreatedBy("System-crm");
        CustomerEntity entity = mapper.convertValue(request, CustomerEntity.class);
        entity.setActive(true);
        CustomerEntity customerEntity = customerRepository.save(entity);
        User user = new User();
        user.setPassword(request.getPassword());
        user.setUsername(request.getEmail());
        user.setActive(true);
        user.setIntroductionCode(customerEntity.getIntroductionCode());
        jwtUserDetailsService.save(user);
    }

    @Override
    public Integer getIntroduceCodeMax() {
        return customerRepository.getIntroduceCodeMax();
    }

    @Override
    public Long getIdCustomerIntroduce(String introduceCode) {
        return customerRepository.getCustomerIdByIntroduceCode(introduceCode);
    }

    @Override
    public String getIntroduceCodeByUsername(String username) throws Exception {
        return customerRepository.getIntroduceCodeByUsername(username);
    }

    @Override
    public List<CustomerRespone> listCustomerByIntroduceCode(String username) throws Exception {
        String introduceCode = getIntroduceCodeByUsername(username);
        return mapper.convertValue(customerRepository.listCustomerByIntroduceCode(introduceCode), new TypeReference<>() {
        });
    }

    @Override
    public CustomerRespone myInfoCustomer(String username) {
        return mapper.convertValue(customerRepository.myInfoCustomer(username),CustomerRespone.class);
    }

}
