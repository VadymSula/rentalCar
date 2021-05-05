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
    @Query(value = "SELECT car.id_car, car.model_id_model, model.model_name " +
            "FROM car JOIN model on car.model_id_model = model.id_model " +
            "JOIN t_user tu on car.user_rent_id_user = tu.id_user " +
            "WHERE car.user_rent_id_user = :idUser",
            nativeQuery = true)
    List<Car> getMyCars(@Param("idUser") Long idUser);
    @Query(value =
            "SELECT car.id_car, car.model_id_model, model.model_name " +
            "FROM car JOIN model on car.model_id_model = model.id_model "+
            "WHERE car.is_free_car = true",
            nativeQuery = true)
    List<Car> getAllFreeCars();

    @Query(value =
            "INSERT INTO car_rating (id_rating, count_of_ratings, rating_car) " +
            "VALUES (DEFAULT, :count :rating)",
            nativeQuery = true)
    void updateRating(@Param("count") Integer countOfRatings,
                      @Param("rating") Double rating);

    @Query(value =
            "SELECT id_car, rating_car, count_of_ratings " +
            "FROM car JOIN car_rating on car.car_rating_id_rating = car_rating.id_rating " +
            "WHERE car.id_car = :idCar",
            nativeQuery = true)
    Car getRatingByIdCar(@Param("idCar") Long idCar);
}
