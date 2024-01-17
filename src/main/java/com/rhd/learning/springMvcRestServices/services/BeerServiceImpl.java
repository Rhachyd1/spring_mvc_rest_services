package com.rhd.learning.springMvcRestServices.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.rhd.learning.springMvcRestServices.model.Beer;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BeerServiceImpl implements BeerService{

    @Override
    public Beer getBeerById(UUID id) {
        log.debug("Im Logging!");
        return Beer.builder()
                .id(id)        
                .version(1)
                .name("A Name")
                .upc("123456")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(10)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
              .build();
    }
    
}
