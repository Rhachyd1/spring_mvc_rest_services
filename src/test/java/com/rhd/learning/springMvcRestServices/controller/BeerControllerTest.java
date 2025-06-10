package com.rhd.learning.springMvcRestServices.controller;


import static org.mockito.ArgumentMatchers.any;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;



import com.rhd.learning.springMvcRestServices.model.Beer;
import com.rhd.learning.springMvcRestServices.services.BeerService;
import com.rhd.learning.springMvcRestServices.services.HeaderService;
import com.rhd.learning.springMvcRestServices.services.implementations.BeerServiceImpl;

import static org.hamcrest.core.Is.is;


//@SpringBootTest
@WebMvcTest(BeerController.class)
public class BeerControllerTest {
    
    @Autowired
    MockMvc mockMvc;
    
    @MockitoBean
    BeerService beerService;

    @MockitoBean
    HeaderService headerService;

    final BeerServiceImpl beerServiceImpl = new BeerServiceImpl();
    final String baseUrl = "/api/v1/beer";
    @Test
    public void getBeerByIdTest() throws Exception{        
       Beer testBeer = beerServiceImpl.listBeers().get(0);
       
       given(beerService.getBeerById(testBeer.getId())).willReturn(testBeer);

       mockMvc.perform(MockMvcRequestBuilders.get(baseUrl+"/"+testBeer.getId())
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id", is(testBeer.getId().toString() ) )  )
        .andExpect(jsonPath("$.name", is(testBeer.getName() ) )  );
       
    }

    @Test
    public void getBeersList() throws Exception{
        List<Beer> beers = beerServiceImpl.listBeers();
        
        given(beerService.listBeers()).willReturn(beers);

        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl)
        .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        //In Array is Length; in List is Size
        .andExpect(jsonPath("$.length()", is(beers.size())));

    }
    
}
