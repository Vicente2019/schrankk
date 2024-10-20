package com.example.backend.user;

import static com.example.backend.exception.ErrorMessage.*;
import com.example.backend.exception.SchrankException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(String username, String password, String email) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new SchrankException(USERNAME_ALREADY_EXISTS, username);
        }
        if (userRepository.findByEmail(email).isPresent()) {
            throw new SchrankException(EMAIL_ALREADY_REGISTERED, email);
        }
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setRoles(Set.of(Role.USER));
        userRepository.save(user);
    }
}
