package com.rhd.learning.springMvcRestServices.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

import com.rhd.learning.springMvcRestServices.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, UUID>{
    
}
