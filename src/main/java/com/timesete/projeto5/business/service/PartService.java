package com.timesete.projeto5.business.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.timesete.projeto5.business.converter.PartConverter;
import com.timesete.projeto5.business.util.Utilities;
import com.timesete.projeto5.model.dto.Part.PartRequest;
import com.timesete.projeto5.model.dto.Part.PartResponse;
import com.timesete.projeto5.model.entity.PartModel;
import com.timesete.projeto5.model.repository.PartRepository;

@Service
public class PartService {

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private Utilities utilities;

    public Page<PartResponse> getAllParts(Pageable pageable) {
        return partRepository.findAll(pageable).map(PartConverter::toResponse);
    }

    public PartResponse createPart(PartRequest request) {
        PartModel model = PartConverter.toModel(request);
        model.setCreatedAt(LocalDateTime.now());
        PartResponse response = PartConverter.toResponse(partRepository.insert(model));
        return response;
    }

    public Boolean deletePart(String id) throws Exception {

        PartModel model = partRepository.findPartById(id)
                .orElseThrow(() -> new RuntimeException("Part not found"));

        partRepository.delete(model);

        return true;
    }

    public PartResponse updatePart(String id, PartRequest request) throws Exception {

        PartModel model = partRepository.findPartById(id)
                .orElseThrow(() -> new RuntimeException("Part not found"));

        PartModel newPart = PartModel.builder()
                .id(id)
                .partnumber(utilities.getOrDefault(request.getPartnumber(), model.getPartnumber()))
                .name(utilities.getOrDefault(request.getName(), model.getName()))
                .price(utilities.getOrDefault(request.getPrice(), model.getPrice()))
                .axleSide(utilities.getOrDefault(request.getAxleSide(), model.getAxleSide()))
                .carModel(utilities.getOrDefault(request.getCarModel(), model.getCarModel()))
                .compability(utilities.getOrDefault(request.getCompability(), model.getCompability()))
                .manufacturer(utilities.getOrDefault(request.getManufacturer(), model.getManufacturer()))
                .minimumStock(utilities.getOrDefault(request.getMinimumStock(), model.getMinimumStock()))
                .state(utilities.getOrDefault(request.getState(), model.getState()))
                .subsystem(utilities.getOrDefault(request.getSubsystem(), model.getSubsystem()))
                .timeInUse(utilities.getOrDefault(request.getTimeInUse(), model.getTimeInUse()))
                .weight(utilities.getOrDefault(request.getWeight(), model.getWeight()))
                .lastModifiedAt(LocalDateTime.now())
                .build();

        partRepository.updatePartById(id, newPart);

        return PartConverter.toResponse(newPart);
    }

    public PartResponse getPartById(String id) {
        return PartConverter.toResponse(partRepository.findPartById(id)
                .orElseThrow(() -> new RuntimeException("Part not found")));
    }
}
