package org.bohdanov.rentalCar.storage;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.TreeMap;

public class TypeOfFuel implements StorageForCarData {
    private final static TreeMap<Integer, String> typesOfFuel = new TreeMap<>();

    static {
        getTypesOfFuel().put(1, "Дизель");
        getTypesOfFuel().put(2, "Бензин");
        getTypesOfFuel().put(3, "Газ/Бензин");
        getTypesOfFuel().put(4, "Електро");
    }

    @Override
    public void addNew(String data) {
        if (isNotExistType(data, getTypesOfFuel())) {
            getTypesOfFuel().put(getTypesOfFuel().lastKey(), data);
        }
    }

    @Override
    public void removeById(Integer id) {
        getTypesOfFuel().remove(id);
    }

    public static TreeMap<Integer, String> getTypesOfFuel() {
        return typesOfFuel;
    }
}
