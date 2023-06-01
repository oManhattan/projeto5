package com.timesete.projeto5.business.converter;

import java.util.List;

import com.timesete.projeto5.model.dto.Part.PartRequest;
import com.timesete.projeto5.model.dto.Part.PartResponse;
import com.timesete.projeto5.model.entity.PartModel;

public class PartConverter {

    public static PartResponse toResponse(PartModel model) {
        return PartResponse.builder()
                .id(model.getId())
                .partnumber(model.getPartnumber())
                .name(model.getName())
                .weight(model.getWeight())
                .state(model.getState())
                .carModel(model.getCarModel())
                .subsystem(model.getSubsystem())
                .manufacturer(model.getManufacturer())
                .axleSide(model.getAxleSide())
                .price(model.getPrice())
                .compability(model.getCompability())
                .timeInUse(model.getTimeInUse())
                .minimumStock(model.getMinimumStock())
                .build();
    }

    public static PartModel toModel(PartRequest request) {
        return PartModel.builder()
                .partnumber(request.getPartnumber())
                .name(request.getName())
                .weight(request.getWeight())
                .state(request.getState())
                .carModel(request.getCarModel())
                .subsystem(request.getSubsystem())
                .manufacturer(request.getManufacturer())
                .axleSide(request.getAxleSide())
                .price(request.getPrice())
                .compability(request.getCompability())
                .timeInUse(request.getTimeInUse())
                .minimumStock(request.getMinimumStock())
                .build();
    }

    public static List<PartResponse> toResponseList(List<PartModel> models) {
        return models.stream().map(model -> toResponse(model)).collect(java.util.stream.Collectors.toList());
    }

}
