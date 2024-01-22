package com.rhd.learning.springMvcRestServices.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rhd.learning.springMvcRestServices.model.Beer;
import com.rhd.learning.springMvcRestServices.services.BeerService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/v1/beer")
public class BeerController {
    
    private final BeerService beerService;

    
    @RequestMapping(method = RequestMethod.GET)
    public List<Beer> listBeers(){
        return beerService.listBeers();
    }
    

    @RequestMapping(value = "{UUID}",method = RequestMethod.GET)
    public Beer getBeer(@PathVariable("UUID") String uuid){
        UUID beerId = UUID.fromString(uuid);
        return beerService.getBeerById(beerId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Beer> handlePost(@RequestBody Beer beer){        
        beerService.createNewBeer(beer);
        return new ResponseEntity<Beer>(HttpStatus.CREATED);
    }
    
}
