package com.timesete.projeto5.business.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timesete.projeto5.model.dto.PartOut.PartOutRequest;
import com.timesete.projeto5.model.entity.CarModel;
import com.timesete.projeto5.model.entity.LocationModel;
import com.timesete.projeto5.model.entity.PartOutModel;
import com.timesete.projeto5.model.entity.SublocationModel;
import com.timesete.projeto5.model.repository.CarRepository;
import com.timesete.projeto5.model.repository.IUPartRepository;
import com.timesete.projeto5.model.repository.LocationRepository;
import com.timesete.projeto5.model.repository.PartOutRepository;
import com.timesete.projeto5.model.repository.SublocationRepository;
import com.timesete.projeto5.model.repository.UserRepository;

@Service
public class PartOutService {

    @Autowired
    private PartOutRepository partOutRepository;

    @Autowired
    private IUPartRepository iuPartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private SublocationRepository sublocationRepository;

    public Boolean createPartOut(PartOutRequest request) throws Exception {

        userRepository.findUserById(request.getUserControl())
                .orElseThrow(() -> new Exception("Could not find control user"));
        userRepository.findUserById(request.getUserWidhdrawal())
                .orElseThrow(() -> new Exception("Could not find withdrawal user"));

        CarModel carModel = carRepository.findCarById(request.getCarId())
                .orElseThrow(() -> new Exception("Could not find car"));

        iuPartRepository.findIUPartById(request.getPartId()).orElseThrow(() -> new Exception("Could not find part"));

        PartOutModel partOutModel = PartOutModel.builder()
                .userControl(request.getUserControl())
                .userWithdrawal(request.getUserWidhdrawal())
                .partId(request.getPartId())
                .carId(request.getCarId())
                .createdAt(LocalDateTime.now())
                .type(request.getType())
                .reason(request.getReason())
                .stage(request.getStage())
                .session(request.getSession())
                .build();

        partOutRepository.save(partOutModel);

        carModel.setLastModifiedAt(LocalDateTime.now());
        carModel.getParts().add(request.getPartId());

        Integer updatedCar = carRepository.updateCarById(carModel.getId(), carModel);

        if (updatedCar <= 0) {
            throw new Exception("Could not add part to car");
        }

        LocationModel location;
        SublocationModel sublocation = new SublocationModel();

        location = locationRepository.findLocationWithPartId(request.getPartId()).orElse(null);

        if (location == null) {
            sublocation = sublocationRepository.findSublocationWithPartId(request.getPartId())
                    .orElseThrow(() -> new Exception("Could not find where the part is stored"));
        }

        if (location != null) {
            location.getParts().remove(request.getPartId());
            locationRepository.updateLocationById(location.getId(), location);
        } else {
            sublocation.getParts().remove(request.getPartId());
            sublocationRepository.updateSublocationById(sublocation.getId(), sublocation);
        }

        return true;
    }
}
