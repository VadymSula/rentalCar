package org.bohdanov.rentalCar.entity.rating;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class CarRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRating;
    @Column(columnDefinition = "decimal(10,2) default '0.00'", nullable = false)
    private Double ratingCar;
    @Column(columnDefinition = "int default '0'", nullable = false)
    private Integer countOfRatings;
}
