package mk.ecode.servers.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ecode.servers.model.User;
import mk.ecode.servers.model.exceptions.InvalidUserIdException;
import mk.ecode.servers.repository.UserRepository;
import mk.ecode.servers.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(InvalidUserIdException::new);
    }

    @Override
    public List<User> listAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(String username, String password, String role) {
        User user = new User();

        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(user.getRole())));
    }
}
