package com.rhd.learning.springMvcRestServices.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;




import com.rhd.learning.springMvcRestServices.model.BeerStyle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Beer {
    
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(length=36, columnDefinition ="varchar", updatable = false, nullable=false)
    @UuidGenerator
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
