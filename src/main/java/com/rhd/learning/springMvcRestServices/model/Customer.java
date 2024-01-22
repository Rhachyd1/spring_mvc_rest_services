package com.rhd.learning.springMvcRestServices.model;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class Customer {
    
    private UUID id;
    private String name;
    private Integer version;
    private LocalDateTime createDate;
    private LocalDateTime lasModifiedDate;



}
