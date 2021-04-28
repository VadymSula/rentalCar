package org.bohdanov.rentalCar.services;

import org.bohdanov.rentalCar.entity.rating.RentalFeedback;
import org.bohdanov.rentalCar.entity.rental.Rental;
import org.bohdanov.rentalCar.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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

    public void addNewReviewAboutRental(RentalFeedback rentalFeedback) {
        rentalRepository.addNewReviewAboutRental(
                rentalFeedback.getLoginName(),
                rentalFeedback.getRating(),
                rentalFeedback.getReviewText()
        );
    }
}
