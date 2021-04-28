package org.bohdanov.rentalCar.services;

import org.bohdanov.rentalCar.entity.car.Car;
import org.bohdanov.rentalCar.entity.rating.CarRating;
import org.bohdanov.rentalCar.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("carService")
public class CarService {

    @Autowired
    @Qualifier("carRepository")
    private CarRepository carRepository;
    @Autowired
    @Qualifier("fileStorageService")
    private FileStorageService fileStorageService;

    public List<Car> getMyCars(Long idUserRent) {
        List<Car> carList = carRepository.findAll();
        carList.forEach(car -> fileStorageService.load(car.getPhotoCar().getPathToFile()));
        return carRepository.getMyCars(idUserRent);
    }
    public void saveNewCarForRental(Car car) {
        car.getPhotoCar().setPathToFile(FileStorageServiceImpl.PATH + car.getPhotoCar().getMultipartFile().getOriginalFilename());
        carRepository.save(car);
    }

    public List<Car> getAllCars() {
        List<Car> carList = carRepository.findAll();
        carList.forEach(car -> fileStorageService.load(car.getPhotoCar().getPathToFile()));
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
