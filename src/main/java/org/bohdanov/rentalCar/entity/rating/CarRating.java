package org.bohdanov.rentalCar.entity.rating;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.bohdanov.rentalCar.entity.car.Car;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class CarRating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idRating;
    @Column(columnDefinition = "decimal(10,2) default '0.00'")
    private Double ratingCar;
    private Integer countOfRatings;
}
