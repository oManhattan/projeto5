package com.timesete.projeto5.model.dto.PartOut;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
@JsonPropertyOrder({ "userControl", "userWidhdrawal", "partId", "carId", "type", "reason", "stage", "session" })
public class PartOutRequest {
    
    @JsonProperty(namespace = "userControl")
    private String userControl;

    @JsonProperty(namespace = "userWidhdrawal")
    private String userWidhdrawal;

    @JsonProperty(namespace = "partId")
    private String partId;

    @JsonProperty(namespace = "carId")
    private String carId;

    @JsonProperty(namespace = "type")
    private String type;

    @JsonProperty(namespace = "reason")
    private String reason;

    @JsonProperty(namespace = "stage")
    private String stage;

    @JsonProperty(namespace = "session")
    private String session;
    
}
