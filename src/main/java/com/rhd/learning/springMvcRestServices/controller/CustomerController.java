package com.rhd.learning.springMvcRestServices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rhd.learning.springMvcRestServices.model.Customer;
import com.rhd.learning.springMvcRestServices.services.CustomerService;
import com.rhd.learning.springMvcRestServices.services.CustomerServiceImpl;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
 
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerServiceImpl customerService){
        this.customerService = customerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> getAllCustomers(){
        return this.customerService.getAllCustomers();
    }

    @RequestMapping(value="{uuid}", method = RequestMethod.GET)
    public Customer getCustomerByID(@PathVariable("uuid") String uuid){        
        return this.customerService.getCustomerById(uuid);
    }

}
