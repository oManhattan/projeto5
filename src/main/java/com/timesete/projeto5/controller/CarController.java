package com.timesete.projeto5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.timesete.projeto5.business.service.CarService;
import com.timesete.projeto5.model.dto.Car.CarRequest;
import com.timesete.projeto5.model.dto.Car.CarResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/car")
public class CarController {
    
    @Autowired
    private CarService carService;

    @PostMapping("/create")
    public ResponseEntity<?> createCar(@RequestBody(required = true) CarRequest request) {
        try {
            CarResponse response = carService.createCar(request);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    
    @GetMapping("/all")
    public ResponseEntity<?> findAllCars() {
        try {
            return ResponseEntity.ok().body(carService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
