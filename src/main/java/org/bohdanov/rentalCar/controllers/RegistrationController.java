package org.bohdanov.rentalCar.controllers;

import org.bohdanov.rentalCar.entity.roles.User;
import org.bohdanov.rentalCar.exceptions.UserNameAlreadyExistException;
import org.bohdanov.rentalCar.models.jwtModels.JwtResponse;
import org.bohdanov.rentalCar.services.security.SecurityService;
import org.bohdanov.rentalCar.services.security.UserService;
import org.bohdanov.rentalCar.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class RegistrationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userDetailsService;

    @PostMapping("/registration")
    public ResponseEntity<?> addUser(@RequestBody User user, BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("registration", HttpStatus.PERMANENT_REDIRECT);
        }
        if (!userService.saveUser(user)) {
            try {
                throw new UserNameAlreadyExistException("UserName already exist!");
            } catch (UserNameAlreadyExistException e) {
                e.printStackTrace();
                return new ResponseEntity<>("registration", HttpStatus.PERMANENT_REDIRECT);
            }
        }
        authenticate(user.getUsername(), user.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(user.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


}
