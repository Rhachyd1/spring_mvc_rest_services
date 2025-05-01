package com.rhd.learning.springMvcRestServices.services.implementations;

import org.springframework.stereotype.Service;

import com.rhd.learning.springMvcRestServices.model.Beer;
import com.rhd.learning.springMvcRestServices.model.Customer;
import com.rhd.learning.springMvcRestServices.services.HeaderService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HeaderServiceImpl implements HeaderService {
    
    @Override
    public String locationBuilder(String baseUrl, Beer beer) {
        StringBuilder location = new StringBuilder(baseUrl).append(beer.getId());
        log.info("Location: " + location.toString());
        return location.toString();
    }
    
    @Override
    public String locationBuilder(String baseUrl, Customer customer) {
        StringBuilder location = new StringBuilder(baseUrl).append(customer.getId());
        log.info("Location: " + location.toString());
        return location.toString();
    }
}
