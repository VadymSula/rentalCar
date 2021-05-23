package org.bohdanov.rentalCar.repositories;

import org.bohdanov.rentalCar.entity.rating.CarRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("ratingRepository")
public interface RatingRepository extends JpaRepository<CarRating, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value =
            "UPDATE car_rating " +
                    "SET count_of_ratings = :count, rating_car = :rating " +
                    "WHERE id_rating = :idRating",
            nativeQuery = true)
    void updateRating(@Param("count") Integer countOfRatings,
                      @Param("rating") Double rating,
                      @Param("idRating") Long idRating);
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value =
            "UPDATE car SET is_free_car = true WHERE id_car = :idCar",
            nativeQuery = true)
    void setTrueOnIsFreeCar(@Param("idCar") Long idCar);
}
