package mk.ecode.events.service.impl;

import mk.ecode.events.model.User;
import mk.ecode.events.model.exceptions.InvalidUserCredentialsException;
import mk.ecode.events.repository.jpa.UserRepository;
import mk.ecode.events.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new RuntimeException("Invalid username or password");
        }

        return userRepository
                .findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }
}
