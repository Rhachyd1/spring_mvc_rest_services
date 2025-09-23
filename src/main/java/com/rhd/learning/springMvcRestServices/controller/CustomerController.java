package com.rhd.learning.springMvcRestServices.controller;

import java.util.List;


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

import com.rhd.learning.springMvcRestServices.model.CustomerDTO;
import com.rhd.learning.springMvcRestServices.services.CustomerService;
import com.rhd.learning.springMvcRestServices.services.HeaderService;
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
    public List<CustomerDTO> getAllCustomers(){
        return this.customerService.getAllCustomers();
    }

    @RequestMapping(value="{uuid}", method = RequestMethod.GET)
    public CustomerDTO getCustomerByID(@PathVariable("uuid") String uuid){        
        return this.customerService.getCustomerById(uuid);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customer){
        HttpHeaders headers = new HttpHeaders();
        CustomerDTO savedCustomer = customerService.createCustomer(customer);
        headers.add("Location", headerService.locationBuilder(baseUrl, savedCustomer));
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<CustomerDTO> putMethodName(@PathVariable String id, @RequestBody CustomerDTO newCustomer) {
        HttpHeaders headers = new HttpHeaders();
        CustomerDTO updatedCustomer = customerService.updateCustomer(id, newCustomer);
        headers.add("Location", headerService.locationBuilder(baseUrl, updatedCustomer));
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<CustomerDTO> handleDelete(@PathVariable("id") String id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PatchMapping("{id}")
    public ResponseEntity<CustomerDTO> handlePatch(@PathVariable("id") String id, CustomerDTO customer){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    
}
