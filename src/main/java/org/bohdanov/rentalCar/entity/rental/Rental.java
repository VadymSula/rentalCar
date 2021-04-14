package org.bohdanov.rentalCar.entity.rental;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.bohdanov.rentalCar.entity.car.Car;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long idRental;
    private Date beginRentalDate;
    private Date endRentalDate;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Car car;
}
