package com.rhd.learning.springMvcRestServices.controller;


import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rhd.learning.springMvcRestServices.model.BeerDTO;
import com.rhd.learning.springMvcRestServices.services.BeerService;
import com.rhd.learning.springMvcRestServices.services.HeaderService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/v1/beer")
public class BeerController {
    
    private final BeerService beerService;
    private final HeaderService headerService;
    private final String baseUrl = "/api/v1/beer/";
    private final String LOCATION = "Location";
    
    @RequestMapping(method = RequestMethod.GET)
    public List<BeerDTO> listBeers(){
        return beerService.listBeers();
    }
    

    @RequestMapping(value = "{UUID}",method = RequestMethod.GET)
    public BeerDTO getBeer(@PathVariable("UUID") String uuid){
        UUID beerId = UUID.fromString(uuid);
        return beerService.getBeerById(beerId).orElseThrow();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<BeerDTO> handlePost(@RequestBody BeerDTO beer){     
        HttpHeaders headers = new HttpHeaders();
        BeerDTO savedBeer = beerService.createNewBeer(beer);
        headers.add(LOCATION, headerService.locationBuilder(baseUrl, savedBeer));
        return new ResponseEntity<BeerDTO>(headers, HttpStatus.CREATED);
    }
    
    @PutMapping(value="{id}")
    public ResponseEntity<BeerDTO> handlePut(@RequestBody BeerDTO beer, @PathVariable String id){
        HttpHeaders headers = new HttpHeaders();
        BeerDTO updatedBeer = beerService.updateBeer(id, beer);
        headers.add(LOCATION, headerService.locationBuilder(baseUrl, updatedBeer));
        return new ResponseEntity<BeerDTO>(headers, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BeerDTO> handleDelete(@PathVariable("id") String id){
        this.beerService.removeBeer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PatchMapping("{id}")
    public ResponseEntity<BeerDTO> handlePatch(@PathVariable("id") String id, BeerDTO beer){
        this.beerService.patchBeer(id, beer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
