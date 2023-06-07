package com.timesete.projeto5.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "car")
public class CarModel {
    
    private String id;
    private String chassi;
    private String model;
    private List<String> parts;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

}
