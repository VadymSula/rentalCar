package org.bohdanov.rentalCar.controllers;

import org.bohdanov.rentalCar.entity.roles.User;
import org.bohdanov.rentalCar.exceptions.UserNameAlreadyExistException;
import org.bohdanov.rentalCar.services.security.SecurityService;
import org.bohdanov.rentalCar.services.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
//    @Qualifier("securityService")
    private SecurityService securityService;

    @PostMapping("/registration")
    public String addUser(@RequestBody User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!userService.saveUser(user)) {
            try {
                throw new UserNameAlreadyExistException("UserName already exist!");
            } catch (UserNameAlreadyExistException e) {
                e.printStackTrace();
                return "registration";
            }
        }
        //TODO JWT-login new!!!
        securityService.autoLogin(user.getUsername(), user.getPassword());
        return "redirect:/";
    }
}
