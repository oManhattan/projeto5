package com.timesete.projeto5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.timesete.projeto5.business.service.PartEntryService;
import com.timesete.projeto5.model.dto.PartEntry.PartEntryRequest;
import com.timesete.projeto5.model.dto.PartEntry.PartEntryResponse;

@RestController
@RequestMapping("/api/v1/part-entry")
public class PartEntryController {
    
    @Autowired
    private PartEntryService partEntryService;

    @PostMapping("/create{amount}")
    public ResponseEntity<?> createPartEntry(@RequestParam(required = false) Integer amount, @RequestBody(required = true) PartEntryRequest request) {
        try {
            PartEntryResponse response = partEntryService.create(request, amount);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
