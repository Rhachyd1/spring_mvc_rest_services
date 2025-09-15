package com.rhd.learning.springMvcRestServices.controller;


import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhd.learning.springMvcRestServices.model.Beer;
import com.rhd.learning.springMvcRestServices.services.BeerService;
import com.rhd.learning.springMvcRestServices.services.HeaderService;
import com.rhd.learning.springMvcRestServices.services.implementations.BeerServiceImpl;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;


//@SpringBootTest // For Broader tests
@WebMvcTest(BeerController.class) //Focused Tests
public class BeerControllerTest {
    
    @Autowired
    MockMvc mockMvc; // Use to test Sprinv MVC

    @Autowired //Using the Object Mapper webContext provides;
    ObjectMapper objectMapper;
    
    /* 
        Ref: https://docs.spring.io/spring-framework/docs/6.2.11/javadoc-api/org/springframework/test/context/bean/override/mockito/MockitoBean.html 
        This brings A Mock into the spring context for the tests.    
    */
    @MockitoBean 
    BeerService beerService;
    

    @MockitoBean
    HeaderService headerService;

    private BeerServiceImpl beerServiceImpl;
    final String baseUrl = "/api/v1/beer";

    @BeforeEach
    void setUp(){
        this.beerServiceImpl = new BeerServiceImpl();
    }


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
    @Test
    public void testCreateNewBeer() throws Exception{     
        Beer beer = beerServiceImpl.listBeers().get(0);
        beer.setVersion(null);
        beer.setId(null);
        
        
        given(beerService.createNewBeer(any(Beer.class)))
        .willReturn(beerServiceImpl.listBeers().get(2));

        //Must Mock the headerService since its a dependency
        given(headerService.locationBuilder(anyString(), any(Beer.class)))
        .willReturn(anyString());
        
        final String testUrl = baseUrl;

        mockMvc.perform(
          MockMvcRequestBuilders
            .post(testUrl)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(beer))
        ).andExpect(
            MockMvcResultMatchers.status().isCreated()
        ).andExpect(
           MockMvcResultMatchers.header().exists("Location")
        );
    }

    @Test
    public void testUpdateBeer() throws Exception{
        Beer beerToUpdate = beerServiceImpl.listBeers().get(0);
        beerToUpdate.setName("updatedName");

        final String testUrl = baseUrl+"/"+beerToUpdate.getId();

        mockMvc.perform(
            MockMvcRequestBuilders.put(testUrl)
                                  .accept(MediaType.APPLICATION_JSON) //The sent content
                                  .contentType(MediaType.APPLICATION_JSON) // The Received content
                                  .content(objectMapper.writeValueAsString(beerToUpdate) ) //The Actual Content
        ).andExpect(MockMvcResultMatchers.status()
                                         .isNoContent()
        );


        verify(beerService).updateBeer(anyString(), any(Beer.class) );
    }

    @Test
    public void testDeleteBeer() throws Exception{
        Beer beerToDelete = beerServiceImpl.listBeers().get(0);
        final String finalUrl = baseUrl+"/"+beerToDelete.getId();
        mockMvc.perform(
            MockMvcRequestBuilders.delete(finalUrl)
        ).andExpect(
            MockMvcResultMatchers.status().isNoContent()
        );
        ArgumentCaptor<String> catchBeer = ArgumentCaptor.forClass(String.class);
        
        verify(beerService).removeBeer(catchBeer.capture());
        assertEquals(beerToDelete.getId().toString(), catchBeer.getValue());
    }
}
