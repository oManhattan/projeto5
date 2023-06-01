package com.timesete.projeto5.model.dto.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.timesete.projeto5.model.entity.UserAccessType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonPropertyOrder({ "name", "login", "password", "accessType" })
public class UserRequest {

    @JsonProperty(namespace = "name")
    private String name;

    @JsonProperty(namespace = "login")
    private String login;

    @JsonProperty("password")
    private String password;

    @JsonProperty("accessType")
    private UserAccessType accessType;

}
