package com.timesete.projeto5.model.dto.Sublocation;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.timesete.projeto5.model.dto.IUPart.IUPartResponse;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@Data
@JsonPropertyOrder({ "id", "name", "pieces" })
public class SublocationResponse {
    
    @JsonProperty(namespace = "id")
    private String id;

    @JsonProperty(namespace = "name")
    private String name;

    @JsonProperty(namespace = "pieces")
    private List<IUPartResponse> pieces;

}
