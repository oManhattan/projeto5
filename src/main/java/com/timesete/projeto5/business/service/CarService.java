package com.timesete.projeto5.business.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timesete.projeto5.model.dto.Car.CarRequest;
import com.timesete.projeto5.model.dto.Car.CarResponse;
import com.timesete.projeto5.model.entity.CarModel;
import com.timesete.projeto5.model.repository.CarRepository;

@Service
public class CarService {
    
    @Autowired
    private CarRepository carRepository;

    public CarResponse createCar(CarRequest request) throws Exception {

        if (carRepository.existsCarByChassi(request.getChassi())) {
            throw new Exception("Car already exists");
        }

        CarModel carModel = CarModel.builder()
        .chassi(request.getChassi())
        .model(request.getModel())
        .parts(new ArrayList<>())
        .createdAt(LocalDateTime.now())
        .build();

        carRepository.save(carModel);

        return CarResponse.builder()
        .id(carModel.getId())
        .chassi(carModel.getChassi())
        .model(carModel.getModel())
        .parts(new ArrayList<>())
        .build();
    }

    public List<CarResponse> findAll() {
        List<CarModel> carModels = carRepository.findAll();
        List<CarResponse> carResponses = new ArrayList<>();

        for (CarModel carModel : carModels) {
            carResponses.add(CarResponse.builder()
            .id(carModel.getId())
            .chassi(carModel.getChassi())
            .model(carModel.getModel())
            .parts(new ArrayList<>())
            .build());
        }

        return carResponses;
    }
}
