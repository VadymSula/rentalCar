package org.bohdanov.rentalCar.models.carDataModels;

import java.util.TreeMap;

public class ColorCar implements StorageForCarData {
    private final static TreeMap<Integer, String> colors = new TreeMap<>();

    static {
        getColors().put(1, "Білий");
        getColors().put(2, "Чорний");
        getColors().put(3, "Синій");
        getColors().put(4, "Червоний");
        getColors().put(5, "Сірий");
        getColors().put(6, "Жовтий");
        getColors().put(7, "Помаранчевий");
        getColors().put(8, "Зелений");
    }

    @Override
    public void addNew(String data) {
        if (isNotExistType(data, getColors())) {
            getColors().put(getColors().lastKey() + 1, data);
        }
    }

    @Override
    public void removeById(Integer id) {
        getColors().remove(id);
    }

    public static TreeMap<Integer, String> getColors() {
        return colors;
    }
}
