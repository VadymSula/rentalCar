package org.bohdanov.rentalCar.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.bohdanov.rentalCar.mappers.CustomMapper;
import org.bohdanov.rentalCar.storage.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

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

    @GetMapping("/free-cars")
    public ResponseEntity<String> getFreeCars() {
        String result = null;
        try {
            result = CustomMapper.getJsonWithRootName("classesOfCar", ClassOfCar.getClassesOfCar());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
