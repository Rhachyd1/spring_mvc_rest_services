package com.rhd.learning.springMvcRestServices.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.rhd.learning.springMvcRestServices.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {


    private HashMap<UUID, Customer> customerFakeBD;

    public CustomerServiceImpl(){
        this.customerFakeBD = new HashMap<>();

        Customer customer1 = Customer.builder()
                .name("A Name")
                .id(UUID.randomUUID())
                .createDate(LocalDateTime.now())
                .lasModifiedDate(LocalDateTime.now())
                .version(new Random().nextInt())
            .build();

        Customer customer2 = Customer.builder()
                .name("Other Name")
                .id(UUID.randomUUID())
                .createDate(LocalDateTime.now())
                .lasModifiedDate(LocalDateTime.now())
                .version(new Random().nextInt())
            .build();

        Customer customer3 = Customer.builder()
                .name("Another Name")
                .id(UUID.randomUUID())
                .createDate(LocalDateTime.now())
                .lasModifiedDate(LocalDateTime.now())
                .version(new Random().nextInt())
            .build();

            this.customerFakeBD.put(customer1.getId(), customer1);
            this.customerFakeBD.put(customer2.getId(), customer2);
            this.customerFakeBD.put(customer3.getId(), customer3);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(this.customerFakeBD.values());
    }

    @Override
    public Customer getCustomerById(String uuid) {
        UUID uuidToFind = UUID.fromString(uuid);
        return this.customerFakeBD.get(uuidToFind);
    }
    
}