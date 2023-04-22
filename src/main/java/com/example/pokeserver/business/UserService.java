package com.example.pokeserver.business;

import com.example.pokeserver.data.User;
import com.example.pokeserver.data.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }
    public List<User> getUsers(){
        return userRepository.findAll();
    }
}
