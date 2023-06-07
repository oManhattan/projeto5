package com.timesete.projeto5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.timesete.projeto5.business.service.LocationService;
import com.timesete.projeto5.model.dto.Location.LocationRequest;
import com.timesete.projeto5.model.dto.Location.LocationResponse;

@RestController
@RequestMapping("/api/v1/location")
public class LocationController {
    
    @Autowired
    LocationService locationService;

    @PostMapping("/create")
    public ResponseEntity<?> createLocation(@RequestBody(required = true) LocationRequest request) {
        try {
            LocationResponse response = locationService.create(request);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllLocations() {
        try {
            List<LocationResponse> response = locationService.getAllLocations();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("find{id}")
    public ResponseEntity<?> findLocationById(@RequestParam(required = true) String id) {
        try {
            LocationResponse response = locationService.getLocationById(id);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
