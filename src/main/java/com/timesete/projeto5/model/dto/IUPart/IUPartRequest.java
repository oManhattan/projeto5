package com.timesete.projeto5.model.dto.IUPart;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"partnumber", "name", "weight", "state", "carModel", "subsystem", "manufacturer", "axleSide", "ncm", "price", "compability", "timeInUse" })
public class IUPartRequest {
    
    @JsonProperty(namespace = "state")
    private String state;

    @JsonProperty(namespace = "timeInUse")
    private Integer timeInUse; 
}
