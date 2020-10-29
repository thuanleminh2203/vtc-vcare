package com.venesa.ctvvcare.repository;

import com.venesa.ctvvcare.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author thuanlm
 * @created at 10/21/2020
 */
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {

    @Query(value = "SELECT max(cast(c.introduction_code as SIGNED )) from customer c",nativeQuery = true)
    Integer getIntroduceCodeMax();

    @Query(value = "SELECT c.customer_id from customer c WHERE c.introduction_code = ?1",nativeQuery = true)
    Long getCustomerIdByIntroduceCode(String introduceCode);

    @Query(value = "SELECT c.introduction_code from customer c WHERE c.email = ?1",nativeQuery = true)
    String getIntroduceCodeByUsername(String username);

    @Query(value = "SELECT * from customer c WHERE c.introduce_customer_code = ?1",nativeQuery = true)
    List<CustomerEntity> listCustomerByIntroduceCode(String introduceCode);

    @Query(value = "SELECT * from customer c WHERE c.email = ?1",nativeQuery = true)
    CustomerEntity myInfoCustomer(String username);
}
