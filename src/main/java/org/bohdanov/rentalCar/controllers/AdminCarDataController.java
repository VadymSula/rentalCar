package org.bohdanov.rentalCar.controllers;

import io.swagger.annotations.ApiOperation;
import org.bohdanov.rentalCar.models.carDataModels.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@CrossOrigin("*")
@RestController
public class AdminCarDataController {
    private static final List<String> typesList =
            Arrays.asList("classesOfCar", "colorsCar", "kppTypes", "typesOfBodyCar", "typesOfFuel");

    @ApiOperation(
            value = "Admin: Get types",
            notes = "Get types, categories, classes of cars"
    )
    @GetMapping("/admin/data-types")
    public ResponseEntity<List<String>> getTypeNames() {
        return new ResponseEntity<>(typesList, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Admin: Add new type",
            notes = "Add new types by data from list of types"
    )
    @PostMapping("/admin/data-of-cars/add/{typeName}")
    public ResponseEntity<HttpStatus> addNewType(
            @RequestPart String data,
            @PathVariable("typeName") String typeName) {
        Objects.requireNonNull(chooseTypeNameForSetData(typeName)).addNew(data);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(
            value = "Admin: Delete data from types",
            notes = "Remove data by type from list of types"
    )
    @DeleteMapping("/admin/data-of-cars/delete/{idData}")
    public ResponseEntity<HttpStatus> removeData(
            @PathVariable("idData") Integer idData,
            @RequestPart String typeName) {
        Objects.requireNonNull(chooseTypeNameForSetData(typeName)).removeById(idData);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private StorageForCarData chooseTypeNameForSetData(String typeName) {
        if      (isEqualsTypes(typeName, 0))
            return new ClassOfCar();
        else if (isEqualsTypes(typeName, 1))
            return new ColorCar();
        else if (isEqualsTypes(typeName, 2))
            return new KppType();
        else if (isEqualsTypes(typeName, 3))
            return new TypeOfBodyCar();
        else if (isEqualsTypes(typeName, 4))
            return new TypeOfFuel();
        return null;
    }

    private boolean isEqualsTypes(String typeName, int index) {
        return typeName.equals(typesList.get(index));
    }
}

