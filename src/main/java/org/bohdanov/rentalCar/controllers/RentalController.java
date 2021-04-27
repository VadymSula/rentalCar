package org.bohdanov.rentalCar.controllers;

import org.bohdanov.rentalCar.entity.rating.RentalReview;
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

    @PostMapping("/cars/add-rental")
    public ResponseEntity<HttpStatus> addNewRentalById(@RequestBody Rental rental) {
        rentalService.addNewRental(rental);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/add-rental-review")
    public ResponseEntity<HttpStatus> addRentalReview(@RequestBody RentalReview rentalReview) {
        SecurityContextHolder.getContext().getAuthentication().getName();
        rentalReview.setLoginName(SecurityContextHolder.getContext().getAuthentication().getName());

        rentalService.addNewReviewAboutRental(rentalReview);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
