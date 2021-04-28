package org.bohdanov.rentalCar.entity.rating;

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
    @Column(columnDefinition = "0.0")
    private Float ratingCar;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Car car;
    private Integer countOfRatings;
}
