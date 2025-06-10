package com.rhd.learning.springMvcRestServices.controller;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.rhd.learning.springMvcRestServices.model.Customer;
import com.rhd.learning.springMvcRestServices.services.CustomerService;
import com.rhd.learning.springMvcRestServices.services.HeaderService;
import com.rhd.learning.springMvcRestServices.services.implementations.CustomerServiceImpl;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    
    @MockitoBean CustomerService customerService;
    @MockitoBean HeaderService headerService;

    @Autowired
    MockMvc mockMvc;

    CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();

    @Test
    public void getCustomerById() throws Exception{
        Customer testCustomer = customerServiceImpl.getAllCustomers().get(0);
        given(customerService.getCustomerById(testCustomer.getId().toString())).willReturn(testCustomer);
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customer/"+testCustomer.getId()).accept(MediaType.APPLICATION_JSON) )
        .andExpectAll(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id", is( testCustomer.getId().toString()) ) )
        .andExpect(jsonPath("$.name", is(testCustomer.getName())));
    }

    @Test
    public void getCustomers() throws Exception{
        List<Customer> customers = customerService.getAllCustomers();
        given(customerService.getAllCustomers()).willReturn(customers);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customer").accept(MediaType.APPLICATION_JSON))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.length()",is(customers.size() ) ) );
    }
}
