package org.bohdanov.rentalCar.repositories;

import org.bohdanov.rentalCar.entity.car.Car;
import org.bohdanov.rentalCar.entity.rating.CarRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("carRepository")
public interface CarRepository extends JpaRepository<Car, Long> {
    @Query(value =
            "SELECT car.id_car, car.model_id_model, model.model_name, cs.is_free_car " +
            "FROM " +
            "car JOIN model on car.model_id_model = model.id_model " +
            "JOIN car_status cs on car.car_status_id_car_status = cs.id_car_status " +
            "WHERE cs.is_free_car = true",
            nativeQuery = true)
    List<Car> getAllFreeCars();

    @Query(value =
            "INSERT INTO car_rating (id_rating, count_of_ratings, rating_car, car_id_car) " +
            "VALUES (DEFAULT, :count :rating, :idCar)",
            nativeQuery = true)
    void updateRating(@Param("count") Integer countOfRatings,
                      @Param("rating") Float rating,
                      @Param("idCar") Long idCar);

    @Query(value =
            "SELECT rating_car, count_of_ratings " +
            "FROM car_rating " +
            "WHERE car_id_car = :idCar",
            nativeQuery = true)
    CarRating getRatingByIdCar(@Param("idCar") Long idCar);
}
