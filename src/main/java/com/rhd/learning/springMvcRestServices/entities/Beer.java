package com.rhd.learning.springMvcRestServices.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


import org.hibernate.annotations.UuidGenerator;

import com.rhd.learning.springMvcRestServices.model.BeerStyle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
@Entity
public class Beer {
    
    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @Column(length=36, columnDefinition ="varchar", updatable = false, nullable=false)
    private UUID id;
    private String name;
    
    @Version
    private Integer version;
    private BeerStyle beerStyle;
    private String upc;
    private Integer quantityOnHand;
    private BigDecimal price;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
