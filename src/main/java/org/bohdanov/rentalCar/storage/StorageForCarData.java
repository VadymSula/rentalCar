package org.bohdanov.rentalCar.storage;

import java.util.TreeMap;

public interface StorageForCarData {
    void addNew(String data);

    void removeById(Integer id);

    default boolean isNotExistType(String data, TreeMap<Integer, String> map) {
        for (String type : map.values()) {
            if (type.equals(data)) {
                throw new IllegalArgumentException("This data already exist");
            }
        }
        return true;
    }
}
