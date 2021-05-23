package org.bohdanov.rentalCar.repositories;

import org.bohdanov.rentalCar.entity.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("carRepository")
public interface CarRepository extends JpaRepository<Car, Long> {
    @Query(value = "SELECT c FROM Car c WHERE c.userRent.idUser = :idUser")
    List<Car> getMyCars(@Param("idUser") Long idUser);

    @Query("SELECT c FROM Car c WHERE c.isFreeCar = true")
    List<Car> getAllFreeCars();
}
