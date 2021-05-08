package org.bohdanov.rentalCar.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiOperation;
import org.bohdanov.rentalCar.entity.car.Model;
import org.bohdanov.rentalCar.mappers.CustomMapper;
import org.bohdanov.rentalCar.models.carDataModels.*;
import org.bohdanov.rentalCar.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
public class DataOfCarsController {

    @Autowired
    @Qualifier("carService")
    private CarService carService;

    @ApiOperation("Get data of cars")
    @GetMapping("/data-of-car")
    public ResponseEntity<CarDataInit> getDataOfCar() {
        CarDataInit carDataInit = new CarDataInit(
                ClassOfCar.getClassesOfCar(),
                ColorCar.getColors(),
                KppType.getKppTypes(),
                TypeOfBodyCar.getTypesOfBody(),
                TypeOfFuel.getTypesOfFuel()
        );
        return new ResponseEntity<>(carDataInit, HttpStatus.OK);
    }

    @ApiOperation("Get list of car models")
    @GetMapping("/cars/models")
    public ResponseEntity<List<Model>> getCarModels() {
        return new ResponseEntity<>(carService.getCarModels(), HttpStatus.OK);
    }
}
