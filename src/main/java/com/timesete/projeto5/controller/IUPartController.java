package com.timesete.projeto5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.timesete.projeto5.business.service.IUPartService;
import com.timesete.projeto5.model.dto.IUPart.IUPartResponse;

@RestController
@RequestMapping("/api/v1/in-use-part")
public class IUPartController {
    
    @Autowired
    private IUPartService iuPartService;

    @GetMapping("/find{id}")
    public ResponseEntity<?> findById(@RequestParam(required = true) String id) {
        try {
            IUPartResponse response = iuPartService.findById(id);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
