package org.bohdanov.rentalCar.repositories;

import org.bohdanov.rentalCar.entity.rating.RentalFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RentFeedbackRepository extends JpaRepository<RentalFeedback, Long> {
    @Query(value = "SELECT * FROM rental_feedback ORDER BY id_review DESC LIMIT 1", nativeQuery = true)
    RentalFeedback getLastFeedback();
}
