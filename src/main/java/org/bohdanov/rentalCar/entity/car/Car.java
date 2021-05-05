package org.bohdanov.rentalCar.entity.car;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.bohdanov.rentalCar.entity.rating.CarRating;
import org.bohdanov.rentalCar.entity.roles.User;

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
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Model model;

    @JsonView(CarView.Public.class)
    @Column(columnDefinition = "boolean default true")
    private Boolean isFreeCar;

    @Column(columnDefinition = "decimal(10,2)")
    private Double engineCapacity;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonView(CarView.Public.class)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CarData carData;

    @JsonView(CarView.Public.class)
    @Column(columnDefinition = "decimal(10,2) default '0.00'")
    private Double priceCar;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CarRating carRating;

    @JsonView(CarView.Internal.class)
    @Column(unique = true, nullable = false)
    private String fileName;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User userRent;
}
