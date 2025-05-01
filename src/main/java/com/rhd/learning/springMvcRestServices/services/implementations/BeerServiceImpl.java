package com.rhd.learning.springMvcRestServices.services.implementations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.rhd.learning.springMvcRestServices.model.Beer;
import com.rhd.learning.springMvcRestServices.services.BeerService;


@Service
public class BeerServiceImpl implements BeerService{

    HashMap<UUID, Beer> beerMap;

    public BeerServiceImpl(){
        this.beerMap = new HashMap<>();
        Beer beer1 = Beer.builder()
            .id(UUID.randomUUID())        
            .version(1)
            .name("A Name")
            .upc("123456")
            .price(new BigDecimal("12.99"))
            .quantityOnHand(10)
            .createdDate(LocalDateTime.now())
            .updateDate(LocalDateTime.now())
        .build();

        Beer beer2 = Beer.builder()
            .id(UUID.randomUUID())        
            .version(1)
            .name("Another Name")
            .upc("654321")
            .price(new BigDecimal("12.99"))
            .quantityOnHand(10)
            .createdDate(LocalDateTime.now())
            .updateDate(LocalDateTime.now())
        .build();

        Beer beer3 = Beer.builder()
            .id(UUID.randomUUID())        
            .version(1)
            .name("Name Another")
            .upc("456123")
            .price(new BigDecimal("12.99"))
            .quantityOnHand(10)
            .createdDate(LocalDateTime.now())
            .updateDate(LocalDateTime.now())
        .build();

        beerMap.put(beer1.getId(), beer1);
        beerMap.put(beer2.getId(), beer2);
        beerMap.put(beer3.getId(), beer3);

    }

    @Override
    public Beer getBeerById(UUID id) {
        
        return beerMap.get(id);
    }
    @Override
    public List<Beer> listBeers(){
        return new ArrayList<>(beerMap.values());
    }

    @Override
    public Beer createNewBeer(Beer beer) {
        beer.setId(UUID.randomUUID());
        beer.setCreatedDate(LocalDateTime.now());
        beer.setVersion(new Random().nextInt());
        beer.setUpdateDate(LocalDateTime.now());
        this.beerMap.put(beer.getId(), beer);
        return beer;
    }
    @Override
    public Beer updateBeer(String id, Beer newBeer){
        UUID idToBeFound = UUID.fromString(id);
        Beer beerToBeUpdated = this.beerMap.get(idToBeFound);

        beerToBeUpdated.setBeerStyle(newBeer.getBeerStyle());
        beerToBeUpdated.setName(newBeer.getName());
        beerToBeUpdated.setPrice(newBeer.getPrice());
        beerToBeUpdated.setQuantityOnHand(newBeer.getQuantityOnHand());
        beerToBeUpdated.setUpc(newBeer.getUpc());
        beerToBeUpdated.setUpdateDate(LocalDateTime.now());

        return beerToBeUpdated;
    }

    @Override
    public void removeBeer(String id) {
       UUID idToBeFound = UUID.fromString(id);
       this.beerMap.remove(idToBeFound);
    }
}
