package org.bohdanov.rentalCar.controllers;

import io.swagger.annotations.ApiOperation;
import org.bohdanov.rentalCar.entity.rating.RentalFeedback;
import org.bohdanov.rentalCar.entity.rental.Rental;
import org.bohdanov.rentalCar.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation(
            value = "Add rental feedback",
            notes = "Add feedback, after his go to -> /cars/set-rating"
    )
    @PostMapping("/add-rental/feedback")
    public ResponseEntity<HttpStatus> addRentalFeedback(@RequestBody RentalFeedback rentalFeedback) {
        rentalFeedback.setLoginName(SecurityContextHolder.getContext().getAuthentication().getName());

        rentalService.addNewReviewAboutRental(rentalFeedback);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
