package org.bohdanov.rentalCar.services.security;

import org.bohdanov.rentalCar.entity.roles.Role;
import org.bohdanov.rentalCar.entity.roles.User;
import org.bohdanov.rentalCar.exceptions.ElementNotFoundException;
import org.bohdanov.rentalCar.repositories.RoleRepository;
import org.bohdanov.rentalCar.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service("userService")
public class UserService implements UserDetailsService {
    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;
    @Autowired
    @Qualifier("roleRepository")
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public User loadUserByName(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public User findUserById(Long userId) throws ElementNotFoundException {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(userFromDb.orElseThrow(ElementNotFoundException::new));
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public void makeUserToAdmin(Long idUser) {
        userRepository.setRoleAdminForUser(idUser);
    }

    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userRepository.findAll().isEmpty()) {
            user.setRoles(Collections.singleton(new Role(3L, "ROLE_ADMIN")));
        } else {
            setRoleByIsRent(user);
        }

        if (userFromDB != null) {
            return false;
        }

        String encode = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        userRepository.save(user);
        return true;
    }

    private void setRoleByIsRent(User user) {
        if (user.isRentRole())
            user.setRoles(Collections.singleton(new Role(2L, "ROLE_USER_BUYER")));
        else
            user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER_RENT")));
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}
