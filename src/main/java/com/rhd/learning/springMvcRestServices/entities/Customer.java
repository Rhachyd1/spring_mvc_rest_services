package com.rhd.learning.springMvcRestServices.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

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

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(length = 36, columnDefinition = "varchar", nullable = false, updatable = false)
    private UUID id;
    
    @Version
    private Integer version;
    
    private String name;        
    private LocalDateTime createDate;
    private LocalDateTime lasModifiedDate;
}
