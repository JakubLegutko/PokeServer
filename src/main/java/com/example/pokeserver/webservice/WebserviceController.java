package com.example.pokeserver.webservice;

import com.example.pokeserver.data.Role;
import com.example.pokeserver.data.User;
import com.example.pokeserver.data.UserRepository;
import com.example.pokeserver.util.DateUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class WebserviceController {
    private final DateUtils dateUtils;
    private final UserRepository userRepository;


    public WebserviceController(DateUtils dateUtils, UserRepository userRepository) {
        this.dateUtils = dateUtils;


        this.userRepository = userRepository;
    }

    @GetMapping("/date")
    public String getDate() {
        return dateUtils.getLocalDateTime();
    }
    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "signup_form";
    }
    @RequestMapping(path="/process_register", method = RequestMethod.POST)
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        Role role = new Role();
        role.setName("USER");
        user.setRoles(Set.of(role)); // Modify to add correct role to user, need logic
        userRepository.save(user);

        return "register_success";
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
    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

}

