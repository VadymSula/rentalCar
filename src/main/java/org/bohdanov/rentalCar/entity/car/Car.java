package org.bohdanov.rentalCar.entity.car;

import lombok.Getter;
import lombok.Setter;
import org.bohdanov.rentalCar.entity.rental.CarStatus;
import org.bohdanov.rentalCar.entity.rental.PriceCar;
import org.bohdanov.rentalCar.entity.rental.Rental;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCar;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Model model;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CarStatus carStatus;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CarData carData;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PriceCar priceCar;
}
