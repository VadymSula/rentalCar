package org.bohdanov.rentalCar.entity.car;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.bohdanov.rentalCar.entity.rental.CarStatus;
import org.bohdanov.rentalCar.entity.rental.PriceCar;
import org.bohdanov.rentalCar.entity.roles.User;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(CarView.Internal.class)
    private Long idCar;
    @JsonView(CarView.Public.class)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Model model;
    @JsonView(CarView.Public.class)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CarStatus carStatus;
    @JsonView(CarView.Public.class)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CarData carData;
    @JsonView(CarView.Public.class)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PriceCar priceCar;
    private String pathToFile;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User userRent;
}
