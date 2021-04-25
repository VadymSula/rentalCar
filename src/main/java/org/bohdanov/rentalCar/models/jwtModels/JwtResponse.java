package org.bohdanov.rentalCar.models.jwtModels;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
@Getter
@ToString
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwtToken;

    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
