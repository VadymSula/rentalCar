package org.bohdanov.rentalCar.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus()
public class FileStorageException extends RuntimeException {
    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
