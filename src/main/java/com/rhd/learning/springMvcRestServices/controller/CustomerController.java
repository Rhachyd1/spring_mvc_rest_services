package com.rhd.learning.springMvcRestServices.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rhd.learning.springMvcRestServices.model.Customer;
import com.rhd.learning.springMvcRestServices.services.CustomerService;
import com.rhd.learning.springMvcRestServices.services.HeaderService;
import com.rhd.learning.springMvcRestServices.services.implementations.CustomerServiceImpl;
import com.rhd.learning.springMvcRestServices.services.implementations.HeaderServiceImpl;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
 
    private final CustomerService customerService;
    private final HeaderService headerService;
    private final String baseUrl="/api/v1/customer/";

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

    @PutMapping("{id}")
    public ResponseEntity<Customer> putMethodName(@PathVariable String id, @RequestBody Customer newCustomer) {
        HttpHeaders headers = new HttpHeaders();
        Customer updatedCustomer = customerService.updateCustomer(id, newCustomer);
        headers.add("Location", headerService.locationBuilder(baseUrl, updatedCustomer));
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Customer> handleDelete(@PathVariable("id") String id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PatchMapping("{id}")
    public ResponseEntity<Customer> handlePatch(@PathVariable("id") String id, Customer customer){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
