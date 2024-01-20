package com.rhd.learning.springMvcRestServices.services;

import java.util.List;
import java.util.UUID;

import com.rhd.learning.springMvcRestServices.model.Beer;

public interface BeerService {

    Beer getBeerById(UUID id);
    List<Beer> listBeers();
} 