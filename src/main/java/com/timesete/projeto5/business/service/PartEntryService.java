package com.timesete.projeto5.business.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timesete.projeto5.business.converter.UserConverter;
import com.timesete.projeto5.model.dto.IUPart.IUPartResponse;
import com.timesete.projeto5.model.dto.PartEntry.PartEntryRequest;
import com.timesete.projeto5.model.dto.PartEntry.PartEntryResponse;
import com.timesete.projeto5.model.entity.LocationModel;
import com.timesete.projeto5.model.entity.PartEntryModel;
import com.timesete.projeto5.model.entity.SublocationModel;
import com.timesete.projeto5.model.entity.UserModel;
import com.timesete.projeto5.model.repository.LocationRepository;
import com.timesete.projeto5.model.repository.PartEntryRepository;
import com.timesete.projeto5.model.repository.SublocationRepository;
import com.timesete.projeto5.model.repository.UserRepository;

@Service
public class PartEntryService {
    
    @Autowired
    private PartEntryRepository partEntryRepository;

    @Autowired
    private IUPartService iuPartService;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private SublocationRepository sublocationRepository;

    @Autowired
    private UserRepository userRepository;

    public PartEntryResponse create(PartEntryRequest request) throws Exception {

        UserModel userModel = userRepository.findById(request.getUserId()).orElseThrow(() -> new Exception("User not found."));

        LocationModel location;
        SublocationModel sublocation = new SublocationModel();

        location = locationRepository.findLocationById(request.getLocationId()).orElse(null);

        if (location == null) {
            sublocation = sublocationRepository.findSublocationById(request.getLocationId()).orElseThrow(() -> new Exception("Location or Sublocation not found."));
        }

        IUPartResponse iupartResponse = iuPartService.createIUPart(request.getPartId(), request.getPartRequest());

        if (location != null) {
            location.getParts().add(iupartResponse.getId());
            locationRepository.updateLocationById(location.getId(), location);
        } else {
            sublocation.getParts().add(iupartResponse.getId());
            sublocationRepository.updateSublocationById(sublocation.getId(), sublocation);
        }
        
        PartEntryModel model = PartEntryModel.builder()
            .partId(iupartResponse.getId())
            .userId(request.getUserId())
            .reason(request.getReason())
            .type(request.getType())
            .createdAt(LocalDateTime.now())
            .build();

        partEntryRepository.save(model);

        return PartEntryResponse.builder()
            .id(model.getId())
            .part(iupartResponse)
            .user(UserConverter.toUserResponse(userModel))
            .reason(model.getReason())
            .type(model.getType())
            .build();
    }



}
