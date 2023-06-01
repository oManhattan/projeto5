package com.timesete.projeto5.business.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.timesete.projeto5.business.converter.PartConverter;
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

        PartModel newModel = PartConverter.toModel(request);

        model.setName(newModel.getName().isEmpty() ? model.getName() : newModel.getName());
        model.setPrice(newModel.getPrice() == null ? model.getPrice() : newModel.getPrice());
        model.setAxleSide(newModel.getAxleSide().isEmpty() ? model.getAxleSide() : newModel.getAxleSide());
        model.setCarModel(newModel.getCarModel().isEmpty() ? model.getCarModel() : newModel.getCarModel());
        model.setCompability(newModel.getCompability().isEmpty() ? model.getCompability() : newModel.getCompability());
        model.setManufacturer(
                newModel.getManufacturer().isEmpty() ? model.getManufacturer() : newModel.getManufacturer());
        model.setMinimumStock(
                newModel.getMinimumStock() == null ? model.getMinimumStock() : newModel.getMinimumStock());
        model.setState(newModel.getState().isEmpty() ? model.getState() : newModel.getState());
        model.setSubsystem(newModel.getSubsystem().isEmpty() ? model.getSubsystem() : newModel.getSubsystem());
        model.setTimeInUse(newModel.getTimeInUse() == null ? model.getTimeInUse() : newModel.getTimeInUse());
        model.setWeight(newModel.getWeight() == null ? model.getWeight() : newModel.getWeight());

        if (partRepository.updatePartById(id, newModel) > 0) {
            return PartConverter.toResponse(model);
        }

        throw new RuntimeException("Part not found");
    }

    public PartResponse getPartById(String id) {
        return PartConverter.toResponse(partRepository.findPartById(id)
                .orElseThrow(() -> new RuntimeException("Part not found")));
    }
}
