package com.example.pokeserver.controllers;

import com.example.pokeserver.data.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import java.util.Collections;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {
    private final UserRepository userRepository;

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

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public User getUserById(@PathVariable Long id) {
        var userFound = userRepository.findById(id);
        return userFound.orElseGet(User::new);
    }
}

