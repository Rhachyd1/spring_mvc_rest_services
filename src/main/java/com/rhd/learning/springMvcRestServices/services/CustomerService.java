package com.rhd.learning.springMvcRestServices.services;

import java.util.List;

import com.rhd.learning.springMvcRestServices.model.Customer;

public interface CustomerService {

    public List<Customer> getAllCustomers();
    public Customer getCustomerById(String uuid);
    
}
