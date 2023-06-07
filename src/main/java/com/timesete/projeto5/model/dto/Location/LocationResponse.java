package com.timesete.projeto5.model.dto.Location;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.timesete.projeto5.model.dto.IUPart.IUPartResponse;
import com.timesete.projeto5.model.dto.Sublocation.SublocationResponse;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonPropertyOrder({ "id", "name", "pieces", "sublocations" })
public class LocationResponse {
    
    @JsonProperty(namespace = "id")
    private String id;

    @JsonProperty(namespace = "name")
    private String name;

    @JsonProperty(namespace = "parts")
    private List<IUPartResponse> parts;

    @JsonProperty(namespace = "sublocations")
    private List<SublocationResponse> sublocations;

}
