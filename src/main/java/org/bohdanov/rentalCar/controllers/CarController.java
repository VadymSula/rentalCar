package org.bohdanov.rentalCar.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.bohdanov.rentalCar.entity.car.Car;
import org.bohdanov.rentalCar.entity.car.CarView;
import org.bohdanov.rentalCar.entity.rating.CarRating;
import org.bohdanov.rentalCar.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class CarController {

    @Autowired
    @Qualifier("carService")
    private CarService carService;

    @GetMapping("/cars")
    @JsonView(CarView.Public.class)
    public ResponseEntity<List<Car>> getAllCars() throws JsonProcessingException {
        //List<Car> cars = new ArrayList<>();
        //ObjectMapper mapper = new ObjectMapper();
//        try {
//            mapper.writerWithView(CarView.Public.class)
//                    .writeValue((JsonGenerator) carService.getAllCars(), cars);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return new ResponseEntity<>(carService.getAllCars(), HttpStatus.OK);
    }

    @GetMapping("/cars/{idCar}")
    public ResponseEntity<?> getCarById(@PathVariable("idCar") Long idCar) {
        Optional<Car> car = carService.getCarById(idCar);
        if (car.isPresent()) {
            return new ResponseEntity<>(car.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().body("Doesn't exist element with id = " + idCar);
        }
    }

    @GetMapping("/cars/free")
    public ResponseEntity<List<Car>> getAllFreeCars() {
        return new ResponseEntity<>(carService.getFreeCars(), HttpStatus.OK);
    }

    @PutMapping("/add-car")
    public ResponseEntity<HttpStatus> addNewCar(@RequestBody Car car) {
        carService.saveNewCarForRental(car);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-car/{idCar}")
    public ResponseEntity<?> deleteCarById(@PathVariable("idCar") Long idCar) {
        try {
            carService.deleteCarById(idCar);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Doesn't exist element with id = " + idCar + " for remove");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/cars/set-rating")
    public ResponseEntity<HttpStatus> updateRatingCarBy(@RequestBody CarRating carRating) {
        carService.updateRatingCar(carRating);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
