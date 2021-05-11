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

    public void updateRatingCar(CarRating carRating) {
        Double newRating = calculateAverageRating(
                getOldRating(carRating.getIdRating()).getRatingCar(),
                carRating.getRatingCar(),
                getOldRating(carRating.getIdRating()).getCountOfRatings()
        );
        ratingRepository.updateRating(
                (getOldRating(carRating.getIdRating()).getCountOfRatings()) + 1,
                newRating,
                carRating.getIdRating());
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
