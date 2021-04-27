package org.bohdanov.rentalCar.controllers;

import com.fasterxml.jackson.annotation.JsonView;
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

    @GetMapping("/cars")
    @JsonView(CarView.Public.class)
    public ResponseEntity<List<Car>> getAllCars() {
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

    @JsonView(CarView.Public.class)
    @PostMapping("/renter/add-car")
    public ResponseEntity<HttpStatus> addNewCar(
            @RequestPart MultipartFile file,
            @RequestPart Car car) {
        fileStorageService.save(file);
        carService.saveNewCarForRental(car);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/renter/{idUser}/my-cars")
    public ResponseEntity<?> getMyCars(@PathVariable("idUser") Long idUser) {
        return new ResponseEntity<>(carService.getMyCars(idUser), HttpStatus.OK);
    }

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

    @PostMapping("/cars/set-rating")
    public ResponseEntity<HttpStatus> updateRatingCarBy(@RequestBody CarRating carRating) {
        carService.updateRatingCar(carRating);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> image() throws IOException {
        final ByteArrayResource inputStream = new ByteArrayResource(Files.readAllBytes(Paths.get(
                "src/main/resources/photos/data.png"
        )));
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentLength(inputStream.contentLength())
                .body(inputStream);

    }
}
