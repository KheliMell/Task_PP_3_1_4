package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.services.dao_services.RoleService;
import ru.kata.spring.boot_security.demo.services.dao_services.UserService;

@Controller
@RequestMapping("/admin")
@Secured("ADMIN") //В случае ошибки (если пропустит обычного пользователя) перехватит и не предоставит доступ
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String adminPanel() {
        return "admin_panel";
    }
}
