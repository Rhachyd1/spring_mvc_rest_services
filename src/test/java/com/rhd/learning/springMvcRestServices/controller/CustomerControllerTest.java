package com.rhd.learning.springMvcRestServices.controller;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhd.learning.springMvcRestServices.model.CustomerDTO;
import com.rhd.learning.springMvcRestServices.services.CustomerService;
import com.rhd.learning.springMvcRestServices.services.HeaderService;
import com.rhd.learning.springMvcRestServices.services.implementations.CustomerServiceImpl;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    
    @MockitoBean CustomerService customerService;
    @MockitoBean HeaderService headerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
    @Captor
    ArgumentCaptor<String> customerCatch;

    private final String BASE_URL ="/api/v1/customer";

    @Test
    public void getCustomerById() throws Exception{
        CustomerDTO testCustomer = customerServiceImpl.getAllCustomers().get(0);
        given(customerService.getCustomerById(testCustomer.getId().toString())).willReturn(testCustomer);
         
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL+"/"+testCustomer.getId()).accept(MediaType.APPLICATION_JSON) )
        .andExpectAll(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id", is( testCustomer.getId().toString()) ) )
        .andExpect(jsonPath("$.name", is(testCustomer.getName())));
    }

    @Test
    public void getCustomers() throws Exception{
        List<CustomerDTO> customers = customerService.getAllCustomers();
        given(customerService.getAllCustomers()).willReturn(customers);
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL).accept(MediaType.APPLICATION_JSON))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.length()",is(customers.size() ) ) );
    }

    @Test
    public void createNewCustomer() throws Exception{
        CustomerDTO newCustomer = customerServiceImpl.getAllCustomers().get(0);
        newCustomer.setId(null);
        newCustomer.setVersion(null);


        given(customerService.createCustomer(any(CustomerDTO.class))).willReturn(customerServiceImpl.getAllCustomers().get(2));
        given(headerService.locationBuilder(anyString(), any(CustomerDTO.class))).willReturn(anyString());

        mockMvc.perform(
            MockMvcRequestBuilders.post(BASE_URL).accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(newCustomer))
        ).andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.header().exists("Location"));
    }

    @Test
    public void updateCustomer() throws Exception{
        CustomerDTO customerToBeUpdated = customerServiceImpl.getAllCustomers().get(0);
        customerToBeUpdated.setName("newName");

        given(customerService.getCustomerById(anyString()))
        .willReturn(customerServiceImpl.getAllCustomers().get(0));

        final String finalUrl =BASE_URL+"/"+customerToBeUpdated.getId();
        mockMvc.perform(
            MockMvcRequestBuilders.put(finalUrl)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customerToBeUpdated))
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void deleteCustomer() throws Exception{
        final CustomerDTO customerToBeRemoved = customerServiceImpl.getAllCustomers().get(0);
        final String finalUrl = BASE_URL+"/"+customerToBeRemoved.getId();

        mockMvc.perform(
            MockMvcRequestBuilders.delete(finalUrl)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());

        //Im Using String because the method's argument is an String
        verify(customerService).deleteCustomer(customerCatch.capture());
        assertEquals(customerToBeRemoved.getId().toString(), customerCatch.getValue());
        
    }
}
