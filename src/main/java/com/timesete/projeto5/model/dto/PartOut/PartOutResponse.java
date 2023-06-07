package com.timesete.projeto5.model.dto.PartOut;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.timesete.projeto5.model.dto.Car.CarResponse;
import com.timesete.projeto5.model.dto.IUPart.IUPartResponse;
import com.timesete.projeto5.model.dto.User.UserResponse;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
@JsonPropertyOrder({ "id", "userControl", "userWidhdraw", "part", "car", "createdAt", "type", "reason", "stage", "session" })
public class PartOutResponse {
    
    @JsonProperty(namespace = "id")
    private String id;

    @JsonProperty(namespace = "userControl")
    private UserResponse userControl;

    @JsonProperty(namespace = "userWidhdraw")
    private UserResponse userWidhdraw;

    @JsonProperty(namespace = "part")
    private IUPartResponse part;

    @JsonProperty(namespace = "car")
    private CarResponse car;

    @JsonProperty(namespace = "createdAt")
    private LocalDateTime createdAt;

    @JsonProperty(namespace = "type")
    private String type;

    @JsonProperty(namespace = "reason")
    private String reason;

    @JsonProperty(namespace = "stage")
    private String stage;

    @JsonProperty(namespace = "session")
    private String session;

}
