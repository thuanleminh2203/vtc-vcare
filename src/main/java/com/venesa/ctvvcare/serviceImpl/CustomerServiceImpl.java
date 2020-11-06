package com.venesa.ctvvcare.serviceImpl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.venesa.ctvvcare.entity.CustomerEntity;
import com.venesa.ctvvcare.entity.User;
import com.venesa.ctvvcare.payload.request.CustomerRequest;
import com.venesa.ctvvcare.payload.response.CustomerResponse;
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
    private final JwtUserDetailsService jwtUserDetailsService;

    @Override
    public void save(CustomerRequest request) throws Exception {
        if(request.getIntroduceCustomerCode() != null && !request.getIntroduceCustomerCode().isEmpty()){
            if(getIdCustomerIntroduce(request.getIntroduceCustomerCode()) == null)
                throw new Exception("Không tìm thấy CTV với mã giới thiệu: " + request.getIntroduceCustomerCode());
        }
        CustomerEntity customer = customerRepository.findCustomerByConditions(request.getPhoneNumber(),request.getIdentifyCard(),request.getBankAccountNumber());
        if(customer != null){
            if(customer.getPhoneNumber().equals(request.getPhoneNumber())){
                throw new Exception("Số điện thoại này đã được sử dụng: " + request.getIntroduceCustomerCode());
            }
            if(customer.getIdentifyCard().equals(request.getIdentifyCard())){
                throw new Exception("Số CMND này đã được sử dụng: " + request.getIdentifyCard());
            }
            if(customer.getBankAccountNumber().equals(request.getBankAccountNumber())){
                throw new Exception("Số tài khoản này đã được sử dụng: " + request.getBankAccountNumber());
            }
        }


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
    public List<CustomerResponse> findAll() {
        return mapper.convertValue(customerRepository.findAll(), new TypeReference<>() {
        });
    }

    @Override
    public List<CustomerResponse> listCustomerByIntroduceCode(String username) throws Exception {
        String introduceCode = getIntroduceCodeByUsername(username);
        return mapper.convertValue(customerRepository.listCustomerByIntroduceCode(introduceCode), new TypeReference<>() {
        });
    }

    @Override
    public CustomerResponse myInfoCustomer(String username) {
        return mapper.convertValue(customerRepository.myInfoCustomer(username), CustomerResponse.class);
    }

}
