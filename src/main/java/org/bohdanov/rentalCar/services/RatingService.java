package org.bohdanov.rentalCar.services;

import org.bohdanov.rentalCar.entity.car.Car;
import org.bohdanov.rentalCar.entity.rating.CarRating;
import org.bohdanov.rentalCar.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("ratingService")
public class RatingService {
    @Autowired
    @Qualifier("ratingRepository")
    private RatingRepository ratingRepository;
    @Autowired
    @Qualifier("rentalService")
    private RentalService rentalService;

    public void updateRatingCar(Car car) {
        Double newRating = calculateAverageRating(
                getOldRating(car.getCarRating().getIdRating()).getRatingCar(),
                car.getCarRating().getRatingCar(),
                getOldRating(car.getCarRating().getIdRating()).getCountOfRatings()
        );
        ratingRepository.updateRating(
                (getOldRating(car.getCarRating().getIdRating()).getCountOfRatings()) + 1,
                newRating,
                car.getCarRating().getIdRating());
        ratingRepository.setTrueOnIsFreeCar(car.getIdCar());
        rentalService.setUnActiveRental(car.getIdCar());
    }

    private CarRating getOldRating(Long idCarRating) {
        return ratingRepository.findById(idCarRating).orElse(null);
    }

    private Double calculateAverageRating(Double oldRating, Double newRating, Integer countOfRatings) {
        if (newRating == null) {
            return oldRating;
        }
        return ((countOfRatings * oldRating) + newRating) / (countOfRatings + 1);
    }
}
