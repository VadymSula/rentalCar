package org.bohdanov.rentalCar.controllers;

import io.swagger.annotations.ApiOperation;
import org.bohdanov.rentalCar.entity.car.Car;
import org.bohdanov.rentalCar.entity.rating.RentalFeedback;
import org.bohdanov.rentalCar.entity.rental.Rental;
import org.bohdanov.rentalCar.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class RentalController {
    @Autowired
    @Qualifier("rentalService")
    private RentalService rentalService;

    @ApiOperation("Add rental")
    @PostMapping("/cars/add-rental")
    public ResponseEntity<HttpStatus> addNewRentalById(@RequestBody Rental rental) {
        rentalService.addNewRental(rental);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation("My rentals")
    @GetMapping("/rentals/{idRent}")
    public ResponseEntity<List<Rental>> getMyRentals(@PathVariable("idRent") Long idRenter) {
        return ResponseEntity.ok(rentalService.getMyRentals(idRenter));
    }

    @ApiOperation(
            value = "Add rental feedback",
            notes = "Add feedback, after his go to -> /cars/set-rating"
    )
    @PostMapping("/end-rental/{idCar}/feedback")
    public ResponseEntity<HttpStatus> addRentalFeedback(
            @PathVariable("idCar") Long idCar,
            @RequestBody RentalFeedback rentalFeedback) {
        rentalFeedback.setLoginName(SecurityContextHolder.getContext().getAuthentication().getName());

        rentalService.addNewReviewAboutRental(idCar, rentalFeedback);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
