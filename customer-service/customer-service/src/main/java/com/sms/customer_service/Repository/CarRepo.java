package com.sms.customer_service.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sms.customer_service.Model.Car;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {

    Car findByPlateNumber(String plateNumber);
    
}