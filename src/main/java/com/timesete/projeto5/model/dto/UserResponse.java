package com.timesete.projeto5.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.timesete.projeto5.model.entity.UserAccessType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonPropertyOrder({"id", "name", "login", "accessType"})
public class UserResponse {

    @JsonProperty(namespace = "id")
    private String id;
    @JsonProperty(namespace = "name")
    private String name;
    @JsonProperty(namespace = "login")
    private String login;
    @JsonProperty(namespace = "accessType")
    private UserAccessType accessType;
}
