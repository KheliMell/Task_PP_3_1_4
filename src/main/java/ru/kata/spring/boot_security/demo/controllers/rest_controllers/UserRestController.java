package ru.kata.spring.boot_security.demo.controllers.rest_controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.models.User;

@RestController
@RequestMapping("/user/api")
public class UserRestController {

    @GetMapping("/current")
    public User getCurrentUser(@AuthenticationPrincipal User user) {
        return user;
    }

}
