package com.timesete.projeto5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.timesete.projeto5.business.service.PartOutService;
import com.timesete.projeto5.model.dto.PartOut.PartOutRequest;

@RestController
@RequestMapping("/api/v1/partout")
public class PartOutController {
    
    @Autowired
    private PartOutService partOutService;

    @PostMapping("/create")
    public ResponseEntity<?> createPartOut(@RequestBody(required = true) PartOutRequest request) {
        try {
            return ResponseEntity.ok().body(partOutService.createPartOut(request));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
