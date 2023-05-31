package com.timesete.projeto5.model.converter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"id", "name", "login", "accessType"})
public class UserResponse {

    @JsonProperty(namespace = "id")
    private Long id;
    @JsonProperty(namespace = "name")
    private String name;
    @JsonProperty(namespace = "login")
    private String login;
    @JsonProperty(namespace = "accessType")
    private String accessType;
}
