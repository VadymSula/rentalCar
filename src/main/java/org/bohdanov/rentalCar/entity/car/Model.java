package org.bohdanov.rentalCar.entity.car;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idModel;
    private String modelName;
    private Integer yearOfIssue;
}
