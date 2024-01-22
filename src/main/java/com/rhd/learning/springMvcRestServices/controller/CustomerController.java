package com.rhd.learning.springMvcRestServices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rhd.learning.springMvcRestServices.model.Customer;
import com.rhd.learning.springMvcRestServices.services.CustomerService;
import com.rhd.learning.springMvcRestServices.services.CustomerServiceImpl;
import com.rhd.learning.springMvcRestServices.services.HeaderService;
import com.rhd.learning.springMvcRestServices.services.HeaderServiceImpl;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
 
    private final CustomerService customerService;
    private final HeaderService headerService;
    private final String baseUrl="/api/v1/customer/";

    @Autowired
    public CustomerController(CustomerServiceImpl customerService, HeaderServiceImpl headerServiceImpl){
        this.customerService = customerService;
        this.headerService = headerServiceImpl;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> getAllCustomers(){
        return this.customerService.getAllCustomers();
    }

    @RequestMapping(value="{uuid}", method = RequestMethod.GET)
    public Customer getCustomerByID(@PathVariable("uuid") String uuid){        
        return this.customerService.getCustomerById(uuid);
    }

    @PostMapping
    public ResponseEntity<Customer> createNewCustomer(@RequestBody Customer customer){
        HttpHeaders headers = new HttpHeaders();
        Customer savedCustomer = customerService.createCustomer(customer);
        headers.add("Location", headerService.locationBuilder(baseUrl, savedCustomer));
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
