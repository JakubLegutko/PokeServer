package com.example.pokeserver.webservice;

import com.example.pokeserver.business.*;
import com.example.pokeserver.data.User;
import com.example.pokeserver.util.DateUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WebserviceController {
    private final DateUtils dateUtils;

    private final RegistrationService registrationService;

    private final UserService userService;
    private final LoginService loginService;

    public WebserviceController(DateUtils dateUtils, RegistrationService registrationService, UserService userService, LoginService loginService) {
        this.dateUtils = dateUtils;

        this.registrationService = registrationService;
        this.userService = userService;

        this.loginService = loginService;
    }


    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<User> getUsers() {
        return this.userService.getUsers();
    }

@RequestMapping(path = "/login", method = RequestMethod.POST)
    public void loginUser(@RequestBody User user) {
        int response =  this.loginService.loginUser(user);
        if (response == 401) {
            throw new RuntimeException("User does not exist");
        } else if (response == 201) {
            System.out.println("User logged in");
        }
    }
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public void registerUser(@RequestBody User user) {
        int response =  this.registrationService.registerUser(user);
        if (response == 401) {
            throw new RuntimeException("User already exists");
        } else if (response == 201) {
            System.out.println("User created");
        }
    }
}

