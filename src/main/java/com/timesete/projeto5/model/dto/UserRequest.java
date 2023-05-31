package com.timesete.projeto5.model.converter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"name", "login", "password", "accessType"})
public class UserRequest {

    @JsonProperty(namespace = "name")
    private String name;

    @JsonProperty(namespace = "login")
    private String login;

    @JsonProperty("password")
    private String password;

    @JsonProperty("accessType")
    private String accessType;

}
