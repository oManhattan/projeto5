package com.timesete.projeto5.business.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timesete.projeto5.business.converter.IUPartConverter;
import com.timesete.projeto5.business.converter.LocationConverter;
import com.timesete.projeto5.business.converter.SublocationConverter;
import com.timesete.projeto5.model.dto.IUPart.IUPartResponse;
import com.timesete.projeto5.model.dto.Location.LocationRequest;
import com.timesete.projeto5.model.dto.Location.LocationResponse;
import com.timesete.projeto5.model.dto.Sublocation.SublocationResponse;
import com.timesete.projeto5.model.entity.LocationModel;
import com.timesete.projeto5.model.entity.SublocationModel;
import com.timesete.projeto5.model.repository.IUPartRepository;
import com.timesete.projeto5.model.repository.LocationRepository;
import com.timesete.projeto5.model.repository.SublocationRepository;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private SublocationRepository sublocationRepository;

    @Autowired
    private IUPartRepository iupartRepository;

    public LocationResponse create(LocationRequest request) throws Exception {

        if (locationRepository.existsByName(request.getName())) {
            throw new Exception("Location already exists");
        }

        LocationModel model = LocationConverter.toModel(request);

        locationRepository.save(model);

        return LocationConverter.toResponse(model);
    }

    public List<LocationResponse> getAllLocations() {

        List<LocationModel> locationModels = locationRepository.findAll();

        List<LocationResponse> locationResponses = locationModels
                .stream()
                .map(location -> {
                    List<SublocationResponse> sublocations = getSublocations(location.getSublocations());
                    List<IUPartResponse> parts = location.getParts()
                            .stream()
                            .map(id -> IUPartConverter.toResponse(iupartRepository.findById(id).orElse(null)))
                            .toList();

                    return LocationResponse.builder()
                    .id(location.getId())
                    .name(location.getName())
                    .sublocations(sublocations)
                    .parts(parts)
                    .build();
                })
                .toList();

        return locationResponses;
    }

    private List<SublocationResponse> getSublocations(List<String> sublocationIds) {

        List<SublocationModel> sublocationModels = sublocationIds
                .stream()
                .map(id -> sublocationRepository.findSublocationById(id).orElse(null))
                .toList();

        List<SublocationResponse> sublocationResponses = sublocationModels
                .stream()
                .map(sublocation -> {
                    List<IUPartResponse> parts = sublocation.getParts()
                            .stream()
                            .map(id -> IUPartConverter.toResponse(iupartRepository.findById(id).orElse(null)))
                            .toList();

                    return SublocationConverter.toResponse(sublocation, parts);
                })
                .toList();

        return sublocationResponses;
    }

    public LocationResponse getLocationById(String id) throws Exception {
        LocationModel model = locationRepository.findLocationById(id).orElseThrow(() -> new RuntimeException("Location not found"));
        return LocationConverter.toResponse(model);
    }

    public LocationResponse insertPart(String locationId, String iupartId) {

        return null;
    }
}
