package com.rhd.learning.springMvcRestServices.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import org.springframework.data.repository.CrudRepository;

import com.rhd.learning.springMvcRestServices.entities.Beer;
public interface BeerRepository extends JpaRepository<Beer, UUID> {
    
}
