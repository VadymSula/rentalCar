package org.bohdanov.rentalCar.repositories;

import org.bohdanov.rentalCar.entity.car.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("modelRepository")
public interface ModelRepository extends JpaRepository<Model, Long> {

    @Query(value = "SELECT * FROM model ORDER BY id_model DESC LIMIT 1", nativeQuery = true)
    Model getLastModel();
}
