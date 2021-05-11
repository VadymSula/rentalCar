package org.bohdanov.rentalCar.repositories;

import org.bohdanov.rentalCar.entity.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("carRepository")
public interface CarRepository extends JpaRepository<Car, Long> {
    @Query(value = "SELECT car.id_car, car.model_id_model, model.model_name " +
            "FROM car JOIN model on car.model_id_model = model.id_model " +
            "JOIN t_user tu on car.user_rent_id_user = tu.id_user " +
            "WHERE car.user_rent_id_user = :idUser",
            nativeQuery = true)
    List<Car> getMyCars(@Param("idUser") Long idUser);

    @Query("SELECT c FROM Car c WHERE c.isFreeCar = true")
    List<Car> getAllFreeCars();
}
