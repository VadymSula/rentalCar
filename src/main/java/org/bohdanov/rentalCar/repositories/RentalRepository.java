package org.bohdanov.rentalCar.repositories;

import org.bohdanov.rentalCar.entity.rental.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository("rentalRepository")
public interface RentalRepository extends JpaRepository<Rental, Long> {
    @Query("SELECT r FROM Rental r WHERE r.userBuyer.idUser = :idRenter")
    List<Rental> getMyRentals(@Param("idRenter") Long idRenter);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Rental r SET r.isActive = false WHERE r.car.idCar = :idCar")
    void unActiveRental(@Param("idCar") Long idCar);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO rental (id_rental, begin_rental_date, end_rental_date, car_id_car, user_buyer_id_user) " +
            "VALUES (DEFAULT, :beginDate, :endDate, :carId, :idBuyer);" +
            "UPDATE car SET is_free_car = false WHERE id_car = :carId", nativeQuery = true)
    void addNewRental(@Param("beginDate") Date beginDate,
                      @Param("endDate") Date endDate,
                      @Param("carId") Long idCar,
                      @Param("idBuyer") Long idBuyer);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO rental_feedback (id_review, login_name, review_text) " +
            "VALUES (DEFAULT, :loginName, :reviewText)", nativeQuery = true)
    void addNewReviewAboutRental(@Param("loginName") String loginName,
                                 @Param("reviewText") String reviewText);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO car_feedbacks (car_id_car, feedbacks_id_review) " +
            "VALUES (:idCar, :idFeedback)", nativeQuery = true)
    void addIdsToCarRentalFeedback(@Param("idCar") Long idCar,
                                   @Param("idFeedback") Long idFeedback);
}
