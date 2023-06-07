package com.timesete.projeto5.model.dto.Car;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.timesete.projeto5.model.dto.Part.PartResponse;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@Data
@JsonPropertyOrder({ "id", "chassi", "model", "parts" })
public class CarResponse {
    
    @JsonProperty(namespace = "id")
    private String id;

    @JsonProperty(namespace = "chassi")
    private String chassi;

    @JsonProperty(namespace = "model")
    private String model;

    @JsonProperty(namespace = "parts")
    private List<PartResponse> parts;

}
