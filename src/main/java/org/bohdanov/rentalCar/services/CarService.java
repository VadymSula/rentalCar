package org.bohdanov.rentalCar.services;

import org.bohdanov.rentalCar.entity.car.Car;
import org.bohdanov.rentalCar.entity.car.Model;
import org.bohdanov.rentalCar.repositories.CarRepository;
import org.bohdanov.rentalCar.repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;
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

    public void deleteCarById(Long idCar) throws FileNotFoundException {
        fileStorageService.deleteByName(Objects.requireNonNull(getCarById(idCar).orElse(null)).getFileName());
        carRepository.deleteById(idCar);
    }
    // TODO Dont work requests with @Query req to db

    public List<Car> getFreeCars() {
        return carRepository.getAllFreeCars();
    }


}
