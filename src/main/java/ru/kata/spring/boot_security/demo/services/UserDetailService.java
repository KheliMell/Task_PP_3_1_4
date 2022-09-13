package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.dao_services.UserServiceImp;

@Service
public class UserDetailService implements UserDetailsService {
    private final UserServiceImp userService;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserDetailService(UserServiceImp userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userService.findUserByEmail(email);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }
}
