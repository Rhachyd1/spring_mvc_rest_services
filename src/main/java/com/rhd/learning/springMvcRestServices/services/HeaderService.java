package com.rhd.learning.springMvcRestServices.services;

import com.rhd.learning.springMvcRestServices.model.BeerDTO;
import com.rhd.learning.springMvcRestServices.model.CustomerDTO;

public interface HeaderService {
    public String locationBuilder(String baseUrl, BeerDTO beer);
    public String locationBuilder(String baseUrl, CustomerDTO customer);
}
