package org.bohdanov.rentalCar.entity.rental;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class CarStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long idCarStatus;
    @Column(columnDefinition = "boolean default true")
    private boolean isFreeCar;
}
