package com.timesete.projeto5.model.dto.PartEntry;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.timesete.projeto5.model.dto.IUPart.IUPartResponse;
import com.timesete.projeto5.model.dto.User.UserResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@JsonPropertyOrder({ "id", "user", "part", "type", "reason" })
public class PartEntryResponse {
    
    @JsonProperty(namespace = "id")
    private String id;

    @JsonProperty(namespace = "user")
    private UserResponse user;

    @JsonProperty(namespace = "part")
    private IUPartResponse part;
    
    @JsonProperty(namespace = "type")
    private String type;

    @JsonProperty(namespace = "reason")
    private String reason;

}
