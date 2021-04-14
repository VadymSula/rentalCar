package org.bohdanov.rentalCar.storage;

import java.util.TreeMap;

public class KppType implements StorageForCarData {
    private static final TreeMap<Integer, String> kppTypes = new TreeMap<>();

    static {
        kppTypes.put(1, "Механіка");
        kppTypes.put(2, "Автомат");
    }

    public static TreeMap<Integer, String> getKppTypes() {
        return kppTypes;
    }

    @Override
    public void addNew(String data) {
        if (isNotExistType(data, getKppTypes())) {
            getKppTypes().put(getKppTypes().lastKey(), data);
        }
    }

    @Override
    public void removeById(Integer id) {
        getKppTypes().remove(id);
    }
}
