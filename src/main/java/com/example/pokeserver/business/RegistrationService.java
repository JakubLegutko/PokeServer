package com.example.pokeserver.business;

import com.example.pokeserver.data.User;
import com.example.pokeserver.data.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final UserRepository userRepository;

    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public int registerUser(User user){
        Iterable<User> users = userRepository.findAll();
        if (users != null) {
            for (User u : users) {
                if (u.getLogin().equals(user.getLogin())) {
                    return 401;
                } else {
                    userRepository.save(user);
                    return 201;
                }
            }
        }
        else {
            userRepository.save(user);
            return 201;
        }
        return -1; // this should never happen
    }
}
