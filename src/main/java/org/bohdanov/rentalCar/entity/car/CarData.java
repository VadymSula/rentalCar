package org.bohdanov.rentalCar.entity.car;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class CarData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long idCarData;
    private Integer idClassOfCar;
    private Integer idColorCar;
    private Integer idKppType;
    private Integer idTypeOfBodyCar;
    private Integer idTypeOfFuel;
}
