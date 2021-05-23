package org.bohdanov.rentalCar.services;

import org.bohdanov.rentalCar.entity.rating.RentalFeedback;
import org.bohdanov.rentalCar.entity.rental.Rental;
import org.bohdanov.rentalCar.repositories.RentFeedbackRepository;
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
    @Autowired
    private RentFeedbackRepository rentFeedbackRepository;

    public void addNewRental(Rental rental) {
        rentalRepository.addNewRental(
                rental.getBeginRentalDate(),
                rental.getEndRentalDate(),
                rental.getCar().getIdCar(),
                rental.getUserBuyer().getIdUser()
        );
    }

    public void addNewReviewAboutRental(Long idCar, RentalFeedback rentalFeedback) {
        rentalRepository.addNewReviewAboutRental(
                rentalFeedback.getLoginName(),
                rentalFeedback.getReviewText()
        );
        rentalRepository.addIdsToCarRentalFeedback(idCar, getLastFeedback().getIdReview());
    }

    public List<Rental> getMyRentals(Long idRenter) {
        return rentalRepository.getMyRentals(idRenter);
    }

    public void setUnActiveRental(Long idCar) {
        rentalRepository.unActiveRental(idCar);
    }

    private RentalFeedback getLastFeedback() {
        return rentFeedbackRepository.getLastFeedback();
    }
}
