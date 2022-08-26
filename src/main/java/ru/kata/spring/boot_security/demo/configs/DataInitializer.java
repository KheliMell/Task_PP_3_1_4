package ru.kata.spring.boot_security.demo.configs;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.dao_services.RoleService;
import ru.kata.spring.boot_security.demo.services.dao_services.UserService;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class DataInitializer {
    private final UserService userService;
    private final RoleService roleService;

    public DataInitializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void init() {
        Role roleAdmin = new Role("ADMIN");
        Role roleUser = new Role("USER");
        roleService.saveRole(roleAdmin);
        roleService.saveRole(roleUser);

        User user_admin = new User(
                "admin",
                "admin",
                (byte) 25,
                "admin",
                "admin@mail.ru",
                roleService.getAllRoles()
        );
        User user_user = new User(
                "user",
                "user",
                (byte) 20,
                "user",
                "user@icloud.com",
                Arrays.asList(roleService.getRoleById(2))
        );
        userService.saveUser(user_admin);
        userService.saveUser(user_user);
    }
}
