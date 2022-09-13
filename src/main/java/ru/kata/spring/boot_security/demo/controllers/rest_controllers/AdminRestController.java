package ru.kata.spring.boot_security.demo.controllers.rest_controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.exception_handling.NoSuchUserException;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.dao_services.RoleService;
import ru.kata.spring.boot_security.demo.services.dao_services.UserService;

import java.util.List;

@RestController
@RequestMapping("/admin/api")
public class AdminRestController {
    private UserService userService;
    private RoleService roleService;

    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users/current")
    public User getCurrentUser(@AuthenticationPrincipal User user){
        return user;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);

        if (user == null) {
            throw new NoSuchUserException("User with ID = " + id + " ,was not found in the database!");
        }

        return user;
    }

    @PostMapping("/users")
    public void addNewUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @PutMapping("/users")
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user.getId(), user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.removeUser(id);
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }
}
