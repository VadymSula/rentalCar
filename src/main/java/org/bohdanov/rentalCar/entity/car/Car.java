package org.bohdanov.rentalCar.entity.car;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.bohdanov.rentalCar.entity.rating.CarRating;
import org.bohdanov.rentalCar.entity.rating.RentalFeedback;
import org.bohdanov.rentalCar.entity.roles.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(CarView.Internal.class)
    private Long idCar;

    @JsonView(CarView.Public.class)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
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
    private String fileName;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<RentalFeedback> feedbacks;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private User userRent;

    @Column(columnDefinition = "TEXT")
    private String specifics;
}
