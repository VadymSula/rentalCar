package org.bohdanov.rentalCar.services;

import org.bohdanov.rentalCar.entity.car.Car;
import org.bohdanov.rentalCar.entity.rating.CarRating;
import org.bohdanov.rentalCar.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("carService")
public class CarService {

    @Autowired
    @Qualifier("carRepository")
    private CarRepository carRepository;

    public void saveNewCarForRental(Car car) {
        carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(Long idCar) {
        return carRepository.findById(idCar);
    }

    public void deleteCarById(Long idCar) {
        carRepository.deleteById(idCar);
    }

    public void updateRatingCar(CarRating newCarRating) {
        Float newRating = calculateAverageRating(
                getOldRating(newCarRating.getCar().getIdCar()).getRatingCar(),
                newCarRating.getRatingCar(),
                getOldRating(newCarRating.getCar().getIdCar()).getCountOfRatings()
        );
        carRepository.updateRating(
                (getOldRating(newCarRating.getCar().getIdCar()).getCountOfRatings()) + 1,
                newRating,
                newCarRating.getCar().getIdCar());

    }

    public List<Car> getFreeCars() {
        return carRepository.getAllFreeCars();
    }

    private CarRating getOldRating(Long idCar) {
        return carRepository.getRatingByIdCar(idCar);
    }

    private Float calculateAverageRating(Float oldRating, Float newRating, Integer countOfRatings) {
        return ((countOfRatings * oldRating) + newRating) / (countOfRatings + 1);
    }

}
