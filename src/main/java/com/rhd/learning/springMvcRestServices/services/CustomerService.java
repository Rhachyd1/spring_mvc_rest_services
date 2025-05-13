package com.rhd.learning.springMvcRestServices.services;

import java.util.List;

import com.rhd.learning.springMvcRestServices.model.Customer;

public interface CustomerService {

    public List<Customer> getAllCustomers();
    public Customer getCustomerById(String uuid);
    public Customer createCustomer(Customer customer);
    public Customer updateCustomer(String id, Customer customer);
    public void deleteCustomer(String id);
    public void patchCustomer(String id, Customer customer);
}
