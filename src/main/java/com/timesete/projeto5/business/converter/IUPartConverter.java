package com.timesete.projeto5.business.converter;

import java.util.List;

import com.timesete.projeto5.model.dto.IUPart.IUPartRequest;
import com.timesete.projeto5.model.dto.IUPart.IUPartResponse;
import com.timesete.projeto5.model.entity.IUPartModel;
import com.timesete.projeto5.model.entity.PartModel;

public class IUPartConverter {

    public static IUPartModel toModel(IUPartRequest request, PartModel model) {
        return IUPartModel.builder()
        .partnumber(model.getPartnumber())
        .name(model.getName())
        .weight(model.getWeight())
        .state(request.getState())
        .carModel(model.getCarModel())
        .subsystem(model.getSubsystem())
        .manufacturer(model.getManufacturer())
        .axleSide(model.getAxleSide())
        .price(model.getPrice())
        .compability(model.getCompability())
        .timeInUse(request.getTimeInUse())
        .build();
    }

    public static IUPartResponse toResponse(IUPartModel model) {
        return IUPartResponse.builder()
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
        .build();
    }

    public static List<IUPartResponse> toResponse(List<IUPartModel> models) {
        return models.stream().map(IUPartConverter::toResponse).toList();
    }
}
