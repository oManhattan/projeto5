package com.timesete.projeto5.model.dto.PartEntry;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.timesete.projeto5.model.dto.IUPart.IUPartRequest;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@JsonPropertyOrder({ "userId", "partId", "locationId", "type", "reason", "partRequest" })
public class PartEntryRequest {
    
    @JsonProperty(namespace = "userId")
    private String userId;

    @JsonProperty(namespace = "partId")
    private String partId;

    @JsonProperty(namespace = "locationId")
    private String locationId;

    @JsonProperty(namespace = "type")
    private String type;

    @JsonProperty(namespace = "reason")
    private String reason;

    @JsonProperty(namespace = "partRequest")
    private IUPartRequest partRequest;
}
