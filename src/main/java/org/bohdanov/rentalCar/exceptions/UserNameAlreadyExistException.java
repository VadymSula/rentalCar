package org.bohdanov.rentalCar.exceptions;

public class UserNameAlreadyExistException extends Exception {
    public UserNameAlreadyExistException(String message) {
        super(message);
    }

    public UserNameAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
