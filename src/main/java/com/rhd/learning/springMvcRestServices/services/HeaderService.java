package com.rhd.learning.springMvcRestServices.services;

import com.rhd.learning.springMvcRestServices.model.Beer;
import com.rhd.learning.springMvcRestServices.model.Customer;

public interface HeaderService {
    public String locationBuilder(String baseUrl, Beer beer);
    public String locationBuilder(String baseUrl, Customer customer);
}
