package org.bohdanov.rentalCar.entity.rental;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.bohdanov.rentalCar.entity.car.Car;
import org.bohdanov.rentalCar.entity.roles.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRental;
    private Date beginRentalDate;
    private Date endRentalDate;
    @Column(columnDefinition = "boolean default true")
    private Boolean isActive;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Car car;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private User userBuyer;
}
