package com.timesete.projeto5.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timesete.projeto5.business.converter.SublocationConverter;
import com.timesete.projeto5.model.dto.Sublocation.SublocationRequest;
import com.timesete.projeto5.model.dto.Sublocation.SublocationResponse;
import com.timesete.projeto5.model.entity.LocationModel;
import com.timesete.projeto5.model.entity.SublocationModel;
import com.timesete.projeto5.model.repository.LocationRepository;
import com.timesete.projeto5.model.repository.SublocationRepository;

@Service
public class SublocationService {
    
    @Autowired
    private SublocationRepository sublocationRepository;

    @Autowired
    private LocationRepository locationRepository;

    public SublocationResponse createSublocation(String locationId, SublocationRequest request) throws Exception {
        
        LocationModel location = locationRepository.findLocationById(locationId).orElseThrow(() -> new Exception("Location not found"));

        if (sublocationRepository.existsByName(request.getName())) {
            throw new Exception("Sublocation already exists");
        }

        SublocationModel model = SublocationConverter.toModel(request);

        sublocationRepository.save(model);

        location.getSublocations().add(model.getId());

        locationRepository.save(location);

        return SublocationConverter.toResponse(model);
    }

    public SublocationResponse getSublocationById(String id) throws Exception {
        SublocationModel model = sublocationRepository.findSublocationById(id).orElseThrow(() -> new RuntimeException("Sublocation not found"));
        return SublocationConverter.toResponse(model);
    }

}
