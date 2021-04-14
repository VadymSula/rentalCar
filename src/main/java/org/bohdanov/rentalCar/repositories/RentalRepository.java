package org.bohdanov.rentalCar.repositories;

import org.bohdanov.rentalCar.entity.rental.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository("rentalRepository")
public interface RentalRepository extends JpaRepository<Rental, Long> {
    @Query(value = "INSERT INTO rental (id_rental, begin_rental_date, end_rental_date, car_id_car) " +
            "VALUES (DEFAULT, :beginDate, :endDate, :carId)", nativeQuery = true)
    void addNewRental(@Param("beginDate") Date beginDate,
                      @Param("endDate") Date endDate,
                      @Param("carId") Long idCar);

    @Query(value =
            "INSERT INTO rental_review (id_review, login_name, rating, review_text) " +
            "VALUES (DEFAULT, :loginName, :rating, :reviewText)", nativeQuery = true)
    void addNewReviewAboutRental(@Param("loginName") String loginName,
                                 @Param("rating") Float rating,
                                 @Param("reviewText") String reviewText);
}
