package com.sms.customer_service.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sms.customer_service.Model.CustomerData;

@Repository

public interface CustomerRepo extends JpaRepository<CustomerData, Long> {

    CustomerData findByPhoneNumber(String phoneNumber);    
} 