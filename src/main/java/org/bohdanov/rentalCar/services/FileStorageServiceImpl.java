package org.bohdanov.rentalCar.services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

@Service("fileStorageService")
public class FileStorageServiceImpl implements FileStorageService {

    public static final Path PATH = Paths.get("src/main/resources/photos/");

    @Override
    public void init() {
        try {
            Files.createDirectory(PATH);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public void save(MultipartFile file) throws FileNotFoundException {
        try {
            Files.copy(file.getInputStream(), PATH.resolve(Objects.requireNonNull(file.getOriginalFilename())));
        } catch (Exception e) {
            throw new FileNotFoundException("Could not store the file");
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = PATH.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(PATH.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files
                    .walk(PATH, 1)
                    .filter(path -> !path.equals(PATH))
                    .map(PATH::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}
