package org.bohdanov.rentalCar.models.carDataModels;

import java.util.TreeMap;

public class ClassOfCar implements StorageForCarData {
    private final static TreeMap<Integer, String> classesOfCar = new TreeMap<>();

    static {
        classesOfCar.put(1, "Економ");
        classesOfCar.put(2, "Стандарт");
        classesOfCar.put(3, "Комфорт");
        classesOfCar.put(4, "Спорт");
    }

    @Override
    public void addNew(String data) {
        if (isNotExistType(data, getClassesOfCar())) {
            getClassesOfCar().put(getClassesOfCar().lastKey() + 1, data);
        }
    }

    @Override
    public void removeById(Integer id) {
        getClassesOfCar().remove(id);
    }

    public static TreeMap<Integer, String> getClassesOfCar() {
        return classesOfCar;
    }
}
