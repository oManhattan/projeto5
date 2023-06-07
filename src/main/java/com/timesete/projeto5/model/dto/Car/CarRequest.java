package com.timesete.projeto5.model.dto.Car;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@JsonPropertyOrder({ "chassi", "model" })
public class CarRequest {

    @JsonProperty(namespace = "chassi")
    private String chassi;

    @JsonProperty(namespace = "model")
    private String model;
}
