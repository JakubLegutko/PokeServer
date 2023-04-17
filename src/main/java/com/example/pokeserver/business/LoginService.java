package com.example.pokeserver.business;

import com.example.pokeserver.data.User;
import com.example.pokeserver.data.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public int loginUser(User user){
        Iterable<User> users = userRepository.findAll();
        if (users != null) {
            for (User u : users) {
                if (u.getLogin().equals(user.getLogin())) {
                    if (u.getPassword().equals(user.getPassword())) {
                        return 201;
                    }
                    else {
                        return 401;
                    }
                }
            }
        }
        return 401;
    }
}
