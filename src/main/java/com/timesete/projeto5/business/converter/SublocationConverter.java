package com.timesete.projeto5.business.converter;

import java.util.ArrayList;
import java.util.List;

import com.timesete.projeto5.model.dto.IUPart.IUPartResponse;
import com.timesete.projeto5.model.dto.Sublocation.SublocationRequest;
import com.timesete.projeto5.model.dto.Sublocation.SublocationResponse;
import com.timesete.projeto5.model.entity.SublocationModel;

public class SublocationConverter {
    
    public static SublocationModel toModel(SublocationRequest request) {
        return SublocationModel.builder()
        .name(request.getName())
        .parts(new ArrayList<>())
        .build();
    }

    public static SublocationResponse toResponse(SublocationModel model, List<IUPartResponse> parts) {
        return SublocationResponse.builder()
        .id(model.getId())
        .name(model.getName())
        .pieces(parts)
        .build();
    }

    public static SublocationResponse toResponse(SublocationModel model) {
        return SublocationResponse.builder()
        .id(model.getId())
        .name(model.getName())
        .build();
    }

    public static List<SublocationResponse> toResponse(List<SublocationModel> models) {
        return models.stream().map(SublocationConverter::toResponse).toList();
    }
}
