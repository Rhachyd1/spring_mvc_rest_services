package com.rhd.learning.springMvcRestServices.services;

import java.util.List;

import com.rhd.learning.springMvcRestServices.model.CustomerDTO;

public interface CustomerService {

    public List<CustomerDTO> getAllCustomers();
    public CustomerDTO getCustomerById(String uuid);
    public CustomerDTO createCustomer(CustomerDTO customer);
    public CustomerDTO updateCustomer(String id, CustomerDTO customer);
    public void deleteCustomer(String id);
    public void patchCustomer(String id, CustomerDTO customer);
}
