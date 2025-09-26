package com.sms.customer_service.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.customer_service.Model.CustomerData;
import com.sms.customer_service.Service.CustomerReadService;
import com.sms.customer_service.Service.CustomerWriteService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/customers")
public class CostomerContoller {
 
    private final CustomerWriteService customerWriteService;
    private final CustomerReadService customerReadService;

    @Autowired
    public CostomerContoller(CustomerWriteService customerWriteService, CustomerReadService customerReadService) {
        this.customerWriteService = customerWriteService;
        this.customerReadService = customerReadService;
    }

  
    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody CustomerData customerData) {

        customerWriteService.createCustomer(customerData);
        return ResponseEntity.ok("Customer created successfully");
    }

    @GetMapping("getAll")
    public ResponseEntity<?>  getAllCutomers() {
        List<CustomerData> customers = customerReadService.getAllCustomers();
        if(customers != null){
            return ResponseEntity.ok(customers);
        }
        return ResponseEntity.status(404).body("No customers found");
    }
}
