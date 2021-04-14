package org.bohdanov.rentalCar.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus()
public class NotFoundException extends RuntimeException {
    public NotFoundException(Object pathVariable) {
        super("For parameter [" + pathVariable.toString() + "] - data not found");
    }

    public NotFoundException() {
        super("Data not found");
    }
}
