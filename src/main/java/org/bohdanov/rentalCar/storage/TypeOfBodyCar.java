package org.bohdanov.rentalCar.storage;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.TreeMap;

public class TypeOfBodyCar implements StorageForCarData {
    private final static TreeMap<Integer, String> typesOfBody = new TreeMap<>();

    static {
        getTypesOfBody().put(1, "Седан");
        getTypesOfBody().put(2, "Хетчбек");
        getTypesOfBody().put(3, "Позашлюховик");
        getTypesOfBody().put(4, "Купе");
        getTypesOfBody().put(5, "Кабріолет");
        getTypesOfBody().put(6, "Універсал");
    }

    @Override
    public void addNew(String data) {
        if (isNotExistType(data, getTypesOfBody())) {
            getTypesOfBody().put(getTypesOfBody().lastKey(), data);
        }
    }

    @Override
    public void removeById(Integer id) {
        getTypesOfBody().remove(id);
    }

    public static TreeMap<Integer, String> getTypesOfBody() {
        return typesOfBody;
    }
}
