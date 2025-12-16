package br.com.larrydev.movieflix.service;

import br.com.larrydev.movieflix.entity.User;
import br.com.larrydev.movieflix.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
