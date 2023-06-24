package com.example.pokeserver.controllers;

import com.example.pokeserver.business.TokenService;
import com.example.pokeserver.data.Role;
import com.example.pokeserver.data.User;
import com.example.pokeserver.data.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public static class NewUserRequest {
        private String email;
        private String password;
        private String firstName;
        private String lastName;
        private String role;

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    public AuthenticationController(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @RequestMapping(path="/register", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody NewUserRequest newUserRequest) {
        //FIXME: KUBA PLZ FIX ME
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
        User newUser = new User();
        newUser.setPassword(newUserRequest.getPassword());
        Role role = new Role();
        role.setName(newUserRequest.getRole());
        newUser.setRoles(Set.of(role)); // Modify to add correct role to user, need logic
        newUser.setEmail(newUserRequest.getEmail());
        newUser.setFirstName(newUserRequest.getFirstName());
        newUser.setLastName(newUserRequest.getLastName());
        userRepository.save(newUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public static class TokenRequestResponse {
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        private String userId;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }

    @RequestMapping(path="/login", method = RequestMethod.POST)
    public ResponseEntity<TokenRequestResponse> login(Authentication authentication) {
        var user = userRepository.findByEmail(authentication.getName());
        String token = tokenService.generateToken(user);
        var response = new TokenRequestResponse();
        response.setToken(token);
        response.setUserId(user.getId().toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
