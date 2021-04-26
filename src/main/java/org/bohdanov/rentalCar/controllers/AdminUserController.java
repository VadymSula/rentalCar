package org.bohdanov.rentalCar.controllers;

import org.bohdanov.rentalCar.entity.roles.User;
import org.bohdanov.rentalCar.exceptions.ElementNotFoundException;
import org.bohdanov.rentalCar.services.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class AdminUserController {
    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @GetMapping("/admin/users")
    public ResponseEntity<List<User>> getUsersList() {
        return new ResponseEntity<>(userService.allUsers(), HttpStatus.OK);
    }

    @GetMapping("/admin/users/{idUser}")
    public ResponseEntity<?> getUserById(@PathVariable("idUser") Long idUser) {
        try {
            return new ResponseEntity<>(userService.findUserById(idUser), HttpStatus.OK);
        } catch (ElementNotFoundException e) {
            return ResponseEntity.badRequest().body("Doesn't exist user with id = " + idUser);
        }

    }

    @DeleteMapping("/admin/users/{idUser}/delete")
    public ResponseEntity<String> deleteUserById(@PathVariable("idUser") Long idUser) {
        if (!userService.deleteUser(idUser)) {
            return ResponseEntity.badRequest().body("Doesn't exist user with id = " + idUser + " for remove");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
