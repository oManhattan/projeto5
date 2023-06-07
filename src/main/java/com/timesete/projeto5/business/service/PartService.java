package com.timesete.projeto5.business.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.timesete.projeto5.business.converter.PartConverter;
import com.timesete.projeto5.business.util.GnUtilities;
import com.timesete.projeto5.model.dto.Part.PartRequest;
import com.timesete.projeto5.model.dto.Part.PartResponse;
import com.timesete.projeto5.model.entity.PartModel;
import com.timesete.projeto5.model.repository.PartRepository;

@Service
public class PartService {

    @Autowired
    private PartRepository partRepository;

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
                .partnumber(GnUtilities.getOrDefault(request.getPartnumber(), model.getPartnumber()))
                .name(GnUtilities.getOrDefault(request.getName(), model.getName()))
                .price(GnUtilities.getOrDefault(request.getPrice(), model.getPrice()))
                .axleSide(GnUtilities.getOrDefault(request.getAxleSide(), model.getAxleSide()))
                .carModel(GnUtilities.getOrDefault(request.getCarModel(), model.getCarModel()))
                .compability(GnUtilities.getOrDefault(request.getCompability(), model.getCompability()))
                .manufacturer(GnUtilities.getOrDefault(request.getManufacturer(), model.getManufacturer()))
                .minimumStock(GnUtilities.getOrDefault(request.getMinimumStock(), model.getMinimumStock()))
                .state(GnUtilities.getOrDefault(request.getState(), model.getState()))
                .subsystem(GnUtilities.getOrDefault(request.getSubsystem(), model.getSubsystem()))
                .timeInUse(GnUtilities.getOrDefault(request.getTimeInUse(), model.getTimeInUse()))
                .weight(GnUtilities.getOrDefault(request.getWeight(), model.getWeight()))
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
