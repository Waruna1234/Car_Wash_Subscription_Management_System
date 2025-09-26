package com.sms.customer_service.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.customer_service.Model.Car;
import com.sms.customer_service.Model.CustomerData;
import com.sms.customer_service.Repository.CustomerRepo;

@Service
public class CustomerWriteService {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CustomerWriteService.class);

    private final CustomerRepo customerRepo;

    private CustomerReadService customerReadService;
    @Autowired
    public CustomerWriteService(CustomerRepo customerRepo, CustomerReadService customerReadService) {
        this.customerRepo = customerRepo;
        this.customerReadService = customerReadService;
    }

    public CustomerData createCustomer(CustomerData customerData) {
        if(customerData == null){
            logger.error("CustomerReadService is not initialized");
            throw new IllegalStateException("CustomerReadService is not initialized");
        }

        List<Car> cars = customerData.getCars();
         
        try {
            if(checkCustomerExists(customerData.getPhoneNumber())){

                for(Car car: cars){
                    if(checkCarExists( car.getPlateNumber())){
                        throw new IllegalStateException("Car with plate number " + car.getPlateNumber() + " already exists for this customer.");
                    }
                    else{
                        logger.info("Adding new car with plate number: {}", car.getPlateNumber());
                        CustomerData createdCustomer = customerRepo.save(customerData);
                    }
                }       
            }else{

                CustomerData createdCustomer = customerRepo.save(customerData);
                logger.info("Creating customer with data: {}", customerData);
                return createdCustomer;
            }   
            return null;      
            
        } catch (Exception e) {
            logger.error("Error creating customer: {}", e.getMessage());
            throw new RuntimeException("Error creating customer", e);
        }        
    }

    public boolean checkCustomerExists(String phoneNumber) {
        return customerReadService.findCustomer(phoneNumber);
    }

    public boolean checkCarExists(String plateNumber) {
        return customerReadService.findCar(plateNumber);
        
    }



    
}
