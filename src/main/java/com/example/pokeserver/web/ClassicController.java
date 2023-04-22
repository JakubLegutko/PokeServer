package com.example.pokeserver.web;

import ch.qos.logback.core.model.Model;
import com.example.pokeserver.business.UserService;
import com.example.pokeserver.data.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ClassicController {
    private final UserService userService;

    public ClassicController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<User> getUsers(Model model) {
        List<User> users = this.userService.getUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
