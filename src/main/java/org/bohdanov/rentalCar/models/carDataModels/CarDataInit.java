package org.bohdanov.rentalCar.models.carDataModels;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CarDataInit {
    private Map<Integer, String> classesOfCar;
    private Map<Integer, String> colorsCar;
    private Map<Integer, String> kppTypes;
    private Map<Integer, String> typesOfBodyCar;
    private Map<Integer, String> typesOfFuel;

    public CarDataInit
            (
             Map<Integer, String> classesOfCar,
             Map<Integer, String> colorsCar,
             Map<Integer, String> kppTypes,
             Map<Integer, String> typesOfBodyCar,
             Map<Integer, String> typesOfFuel
            ) {
        this.classesOfCar = classesOfCar;
        this.colorsCar = colorsCar;
        this.kppTypes = kppTypes;
        this.typesOfBodyCar = typesOfBodyCar;
        this.typesOfFuel = typesOfFuel;
    }
}
