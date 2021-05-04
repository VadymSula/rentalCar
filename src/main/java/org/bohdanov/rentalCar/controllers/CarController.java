package org.bohdanov.rentalCar.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import org.bohdanov.rentalCar.entity.car.Car;
import org.bohdanov.rentalCar.entity.car.CarView;
import org.bohdanov.rentalCar.entity.rating.CarRating;
import org.bohdanov.rentalCar.services.CarService;
import org.bohdanov.rentalCar.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
public class CarController {

    @Autowired
    @Qualifier("carService")
    private CarService carService;
    @Autowired
    @Qualifier("fileStorageService")
    private FileStorageService fileStorageService;

    @ApiOperation("Get all cars")
    @GetMapping("/cars")
    @JsonView(CarView.Public.class)
    public ResponseEntity<List<Car>> getAllCars() {
        return ResponseEntity
                .ok()
                .body(carService.getAllCars());
    }

    @ApiOperation("Get car by Id")
    @GetMapping("/cars/{idCar}")
    public ResponseEntity<?> getCarById(@PathVariable("idCar") Long idCar) {
        Optional<Car> car = carService.getCarById(idCar);
        if (car.isPresent()) {
            return new ResponseEntity<>(car.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().body("Doesn't exist element with id = " + idCar);
        }
    }

    @ApiOperation("Get all free cars for rental")
    @GetMapping("/cars/free")
    public ResponseEntity<List<Car>> getAllFreeCars() {
        return new ResponseEntity<>(carService.getFreeCars(), HttpStatus.OK);
    }

    //TODO test: after added new car, rating must be == 0
    @ApiOperation(
            value = "Renter: Add car",
            notes = "Renter can add new car and choose photo for her"
    )
    @JsonView(CarView.Public.class)
    @PostMapping("/renter/add-car")
    public ResponseEntity<HttpStatus> addNewCar(@RequestBody Car car) {
        carService.saveNewCarForRental(car);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("Renter: Second request for adding car")
    @PutMapping("/renter/add-car")
    public ResponseEntity<HttpStatus> putPngForCar(@RequestPart MultipartFile file) {
        fileStorageService.save(file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(
            value = "Renter: Get my cars",
            notes = "Get renter's cars by Id"
    )
    @GetMapping("/renter/{idUser}/my-cars")
    public ResponseEntity<?> getMyCars(@PathVariable("idUser") Long idUser) {
        return new ResponseEntity<>(carService.getMyCars(idUser), HttpStatus.OK);
    }

    @ApiOperation("Renter: Delete car by Id")
    @DeleteMapping("/renter/my-cars/delete-car/{idCar}")
    public ResponseEntity<?> deleteCarById(@PathVariable("idCar") Long idCar) {
        try {
            carService.deleteCarById(idCar);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Doesn't exist element with id = " + idCar + " for remove");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(
            value = "Set rating for car",
            notes = "User can set rating after rent end (from 0 to 5 stars)"
    )
    @PostMapping("/cars/set-rating")
    public ResponseEntity<HttpStatus> updateRatingCarBy(@RequestBody CarRating carRating) {
        carService.updateRatingCar(carRating);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation("Path to image by 'fileName'")
    @GetMapping(
            value = "/image/{fileName}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public ResponseEntity<?> image(@PathVariable("fileName") String fileName)
            throws IOException {
        final ByteArrayResource inputStream = new ByteArrayResource(Files.readAllBytes(Paths.get(
                "src/main/resources/photos/" + fileName
        )));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inputStream);
    }

}
