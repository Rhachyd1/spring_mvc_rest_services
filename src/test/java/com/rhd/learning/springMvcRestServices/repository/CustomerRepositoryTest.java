package com.rhd.learning.springMvcRestServices.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.rhd.learning.springMvcRestServices.entities.Customer;

@DataJpaTest//Autoconfigure everything
public class CustomerRepositoryTest {

    @Autowired//creates at runtime repo code;
    private CustomerRepository repository;

    @Test
    public void saveNewCustomer(){
        Customer savedCustomer = repository.save(Customer.builder().name("New Customer").build());
        assertNotNull(savedCustomer);
        assertNotNull(savedCustomer.getId());
    }
}
