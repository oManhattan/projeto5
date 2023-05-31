package com.timesete.projeto5.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"login", "password"})
public class LoginRequest {

    @JsonProperty(namespace = "login")
    private String login;
    @JsonProperty(namespace = "password")
    private String password;
}
