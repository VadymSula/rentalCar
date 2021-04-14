package org.bohdanov.rentalCar.entity.rating;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class RentalReview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idReview;
    @Lob
    private String reviewText;
    private String loginName;
    private Float rating;
}
