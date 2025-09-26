package com.sms.customer_service.Service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.customer_service.Model.CustomerData;
import com.sms.customer_service.Repository.CarRepo;
import com.sms.customer_service.Repository.CustomerRepo;


@Service
public  class CustomerReadService {

    private static final Logger logger = Logger.getLogger(CustomerReadService.class.getName());

    private final CustomerRepo customerRepo;
    private final CarRepo carRepo;

    @Autowired
    public CustomerReadService(CustomerRepo customerRepo, CarRepo carRepo) {
        this.customerRepo = customerRepo;
        this.carRepo = carRepo;
    }


    public boolean findCustomer(String phoneNumber) {
        if(customerRepo.findByPhoneNumber(phoneNumber) != null){
            return true;
        }
        return false;
    }

    public boolean findCar(String plateNumber) {
        if(carRepo.findByPlateNumber(plateNumber) != null){
            return true;
        }
        return false;
    }

    public List<CustomerData> getAllCustomers() {
        return customerRepo.findAll();
    }

    
}
