package com.rhd.learning.springMvcRestServices.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.rhd.learning.springMvcRestServices.model.BeerDTO;

public interface BeerService {

    Optional<BeerDTO> getBeerById(UUID id);
    List<BeerDTO> listBeers();
    BeerDTO createNewBeer(BeerDTO beer);
    BeerDTO updateBeer(String id, BeerDTO newBeer);
    void removeBeer(String id);
    void patchBeer(String id, BeerDTO patchBeer);
} 