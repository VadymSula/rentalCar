package org.bohdanov.rentalCar.storage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilesInfo {
    private String name;
    private String url;

    public FilesInfo(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
