package com.timesete.projeto5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.timesete.projeto5.business.service.PartService;
import com.timesete.projeto5.model.dto.Part.PartRequest;

@RestController
@RequestMapping("/api/v1/part")
public class PartController {

    @Autowired
    private PartService partService;

    @GetMapping("/find{id}")
    public ResponseEntity<?> findPartById(@RequestParam(required = true, name = "id") String id) {
        try {
            return ResponseEntity.ok().body(partService.getPartById(id));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllParts(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            return ResponseEntity.ok().body(partService.getAllParts(PageRequest.of(page, size)));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPart(@RequestBody(required = true) PartRequest request) {
        try {
            return ResponseEntity.ok().body(partService.createPart(request));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/update{id}")
    public ResponseEntity<?> updatePart(@RequestParam(required = true, name = "id") String id,
            @RequestBody(required = true) PartRequest request) {
        try {
            return ResponseEntity.ok().body(partService.updatePart(id, request));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete{id}")
    public ResponseEntity<?> deletePart(@RequestParam(required = true, name = "id") String id) {
        try {
            return ResponseEntity.ok().body(partService.deletePart(id));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
