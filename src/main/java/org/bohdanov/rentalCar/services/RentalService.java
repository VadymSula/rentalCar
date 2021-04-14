package org.bohdanov.rentalCar.services;

import org.bohdanov.rentalCar.entity.car.Car;
import org.bohdanov.rentalCar.entity.rating.RentalReview;
import org.bohdanov.rentalCar.entity.rental.Rental;
import org.bohdanov.rentalCar.repositories.CarRepository;
import org.bohdanov.rentalCar.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("rentalService")
public class RentalService {
    @Autowired
    @Qualifier("rentalRepository")
    private RentalRepository rentalRepository;



    public void addNewRental(Rental rental) {
        rentalRepository.addNewRental(
                rental.getBeginRentalDate(),
                rental.getEndRentalDate(),
                rental.getCar().getIdCar()
        );
    }

    public void addNewReviewAboutRental(RentalReview rentalReview) {
        rentalRepository.addNewReviewAboutRental(
                rentalReview.getLoginName(),
                rentalReview.getRating(),
                rentalReview.getReviewText()
        );
    }
}
