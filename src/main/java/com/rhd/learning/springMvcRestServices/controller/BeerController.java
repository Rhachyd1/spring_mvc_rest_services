package com.rhd.learning.springMvcRestServices.controller;

import java.util.UUID;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rhd.learning.springMvcRestServices.services.BeerService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@CrossOrigin
public class BeerController {
    
    private final BeerService beerService;

    @GetMapping("/beer")
    public String getBeer(){
        return beerService.getBeerById(UUID.randomUUID()).toString();
    }
}
