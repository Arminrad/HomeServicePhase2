package com.phase2.homeService.service.implementations;

import com.phase2.homeService.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImple implements com.phase2.homeService.service.interfaces.UserService {

    private final UserRepository userRepository;

    public UserServiceImple(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Boolean findByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
