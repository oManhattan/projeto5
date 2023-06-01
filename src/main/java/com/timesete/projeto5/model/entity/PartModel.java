package com.timesete.projeto5.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "part")
public class PartModel {
  
    private String id;
    private String partnumber;
    private String name;
    private Float weight;
    private String state;
    private String carModel;
    private String subsystem;
    private String manufacturer;
    private String axleSide;
    private Float price;
    private List<String> compability;
    private Integer timeInUse;
    private PartMinimumStock minimumStock;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
}
