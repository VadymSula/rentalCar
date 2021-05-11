package org.bohdanov.rentalCar.entity.rating;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class RentalFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReview;
    @Column(columnDefinition = "TEXT")
    private String reviewText;
    private String loginName;
}
