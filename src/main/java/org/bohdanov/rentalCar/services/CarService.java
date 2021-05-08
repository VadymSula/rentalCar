package org.bohdanov.rentalCar.services;

import org.bohdanov.rentalCar.entity.car.Car;
import org.bohdanov.rentalCar.entity.car.Model;
import org.bohdanov.rentalCar.repositories.CarRepository;
import org.bohdanov.rentalCar.repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("carService")
public class CarService {

    @Autowired
    @Qualifier("modelRepository")
    private ModelRepository modelRepository;
    @Autowired
    @Qualifier("carRepository")
    private CarRepository carRepository;
    @Autowired
    @Qualifier("fileStorageService")
    private FileStorageService fileStorageService;

    public List<Car> getMyCars(Long idUserRent) {
        return carRepository.getMyCars(idUserRent);
    }

    public void saveNewCarForRental(Car car) {
        //car.setPathToFile(FileStorageServiceImpl.PATH.resolve(Objects.requireNonNull(car.getPathToFile())).toString());
        carRepository.save(car);
    }

    public List<Model> getCarModels() {
        return modelRepository.findAll();
    }

    public Model saveNewModelOfCarAndGetHer(Model model) {
        modelRepository.save(model);
        return modelRepository.getLastModel();

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

    // TODO Dont work requests with @Query req to db
    public void updateRatingCar(Car car) {
        Double newRating = calculateAverageRating(
                getOldRating(car.getIdCar()).getCarRating().getRatingCar(),
                car.getCarRating().getRatingCar(),
                getOldRating(car.getIdCar()).getCarRating().getCountOfRatings()
        );
        carRepository.updateRating(
                (getOldRating(car.getIdCar()).getCarRating().getCountOfRatings()) + 1,
                newRating);
    }

    public List<Car> getFreeCars() {
        return carRepository.getAllFreeCars();
    }

    private Car getOldRating(Long idCar) {
        return carRepository.getRatingByIdCar(idCar);
    }

    private Double calculateAverageRating(Double oldRating, Double newRating, Integer countOfRatings) {
        return ((countOfRatings * oldRating) + newRating) / (countOfRatings + 1);
    }
}
