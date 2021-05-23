package org.bohdanov.rentalCar.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorageService {
    void init();

    void save(MultipartFile file) throws FileNotFoundException;

    Resource load(String filename);

    void deleteAll();

    void deleteByName(String filename) throws FileNotFoundException;

    Stream<Path> loadAll();
}
