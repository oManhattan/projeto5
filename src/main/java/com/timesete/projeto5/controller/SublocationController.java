package com.timesete.projeto5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.timesete.projeto5.business.service.SublocationService;
import com.timesete.projeto5.model.dto.Sublocation.SublocationRequest;
import com.timesete.projeto5.model.dto.Sublocation.SublocationResponse;

@RestController
@RequestMapping("/api/v1/sublocation")
public class SublocationController {
    
    @Autowired
    private SublocationService sublocationService;

    @PostMapping("/create{id}")
    public ResponseEntity<?> createSublocation(@RequestParam(required = true, name = "id") String locationId, @RequestBody(required = true) SublocationRequest request) {
        try {
            SublocationResponse response = sublocationService.createSublocation(locationId, request);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/find{id}")
    public ResponseEntity<?> getSublocationById(@RequestParam(required = true, name = "id") String id) {
        try {
            SublocationResponse response = sublocationService.getSublocationById(id);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
