package com.timesete.projeto5.model.dto.Part;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.timesete.projeto5.model.entity.PartMinimumStock;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@JsonPropertyOrder({ "id", "partnumber", "name", "weight", "state", "carModel", "subsystem", "manufacturer", "axleSide", "price", "compability", "timeInUse", "minimumStock" })
public class PartResponse {

    @JsonProperty(namespace = "id")
    private String id;

    @JsonProperty(namespace = "partnumber")
    private String partnumber;

    @JsonProperty(namespace = "name")
    private String name;

    @JsonProperty(namespace = "weight")
    private Float weight;

    @JsonProperty(namespace = "state")
    private String state;

    @JsonProperty(namespace = "carModel")
    private String carModel;

    @JsonProperty(namespace = "subsystem")
    private String subsystem;

    @JsonProperty(namespace = "manufacturer")
    private String manufacturer;

    @JsonProperty(namespace = "axleSide")
    private String axleSide;

    @JsonProperty(namespace = "price")
    private Float price;

    @JsonProperty(namespace = "compability")
    private List<String> compability;

    @JsonProperty(namespace = "timeInUse")
    private Integer timeInUse;

    @JsonProperty(namespace = "minimumStock")
    private PartMinimumStock minimumStock;

}
