package com.rhd.learning.springMvcRestServices.services.implementations;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.rhd.learning.springMvcRestServices.model.CustomerDTO;
import com.rhd.learning.springMvcRestServices.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {


    private HashMap<UUID, CustomerDTO> customerFakeBD;

    public CustomerServiceImpl(){
        this.customerFakeBD = new HashMap<>();

        CustomerDTO customer1 = CustomerDTO.builder()
                .name("A Name")
                .id(UUID.randomUUID())
                .createDate(LocalDateTime.now())
                .lasModifiedDate(LocalDateTime.now())
                .version(new Random().nextInt())
            .build();

        CustomerDTO customer2 = CustomerDTO.builder()
                .name("Other Name")
                .id(UUID.randomUUID())
                .createDate(LocalDateTime.now())
                .lasModifiedDate(LocalDateTime.now())
                .version(new Random().nextInt())
            .build();

        CustomerDTO customer3 = CustomerDTO.builder()
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
    public List<CustomerDTO> getAllCustomers() {
        return new ArrayList<>(this.customerFakeBD.values());
    }

    @Override
    public CustomerDTO getCustomerById(String uuid) {
        UUID uuidToFind = UUID.fromString(uuid);
        return this.customerFakeBD.get(uuidToFind);
    }
    
    @Override
    public CustomerDTO createCustomer(CustomerDTO customer){
        customer.setId(UUID.randomUUID());
        customer.setVersion(new Random().nextInt());
        customer.setCreateDate(LocalDateTime.now());
        customer.setLasModifiedDate(LocalDateTime.now());
        customerFakeBD.put(customer.getId(), customer);
        return customer;

    }

    @Override
    public CustomerDTO updateCustomer(String id, CustomerDTO newCustomer){
        UUID idToBeFound = UUID.fromString(id);
        CustomerDTO customerToBeUpdated = this.customerFakeBD.get(idToBeFound);
        
        customerToBeUpdated.setName(newCustomer.getName());
        customerToBeUpdated.setLasModifiedDate(LocalDateTime.now());

        return customerToBeUpdated;
    }

    @Override
    public void deleteCustomer(String id) {
        UUID idToBeFound = UUID.fromString(id);
        this.customerFakeBD.remove(idToBeFound);
    }
    @Override
    public void patchCustomer(String id, CustomerDTO customer){
        UUID idToBeFound = UUID.fromString(id);
        CustomerDTO customerToBeUpdated = this.customerFakeBD.get(idToBeFound);
        if(Objects.nonNull(customer.getName())){
            customerToBeUpdated.setName(customer.getName());
        }
        customerToBeUpdated.setLasModifiedDate(LocalDateTime.now());
    }
}
