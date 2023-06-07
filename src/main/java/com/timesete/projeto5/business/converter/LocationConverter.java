package com.timesete.projeto5.business.converter;

import java.util.ArrayList;

import com.timesete.projeto5.model.dto.Location.LocationRequest;
import com.timesete.projeto5.model.dto.Location.LocationResponse;
import com.timesete.projeto5.model.entity.LocationModel;

public class LocationConverter {
    
    public static LocationModel toModel(LocationRequest request) {
        return LocationModel.builder()
        .name(request.getName())
        .parts(new ArrayList<>())
        .sublocations(new ArrayList<>())
        .build();
    }

    public static LocationResponse toResponse(LocationModel model) {
        return LocationResponse.builder()
        .id(model.getId())
        .name(model.getName())
        .build();
    }

    
}
