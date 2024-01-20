package com.rhd.learning.springMvcRestServices.controller;

import java.util.List;
import java.util.UUID;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rhd.learning.springMvcRestServices.model.Beer;
import com.rhd.learning.springMvcRestServices.services.BeerService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@CrossOrigin
public class BeerController {
    
    private final BeerService beerService;

    
    @GetMapping("/api/v1/beer")
    public List<Beer> listBeers(){
        return beerService.listBeers();
    }
    

    @GetMapping("/api/v1/beer/{UUID}")
    public Beer getBeer(@PathVariable("UUID") String uuid){
        UUID beerId = UUID.fromString(uuid);
        return beerService.getBeerById(beerId);
    }
    
}
