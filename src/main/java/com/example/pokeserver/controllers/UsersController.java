package com.example.pokeserver.controllers;

import com.example.pokeserver.data.Role;
import com.example.pokeserver.data.User;
import com.example.pokeserver.data.UserRepository;
import com.example.pokeserver.util.DateUtils;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private final UserRepository userRepository;

    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(path="/process_register_admin", method = RequestMethod.POST)
    public String processRegisterAdmin(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        Role role = new Role();
        role.setName("USER");
        Role role2 = new Role();
        role2.setName("LEAGUE_ADMIN");
        user.setRoles(Set.of(role,role2)); // Modify to add correct role to user, need logic
        userRepository.save(user);

        return "register_success";
    }

    //@RolesAllowed("ADMIN")
    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }
}

