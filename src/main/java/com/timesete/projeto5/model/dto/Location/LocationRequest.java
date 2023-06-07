package com.timesete.projeto5.model.dto.Location;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({ "name" })
public class LocationRequest {

    @JsonProperty(namespace = "name")
    private String name;
    
}
