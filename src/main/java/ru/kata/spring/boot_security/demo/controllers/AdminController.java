package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.dao_services.RoleService;
import ru.kata.spring.boot_security.demo.services.dao_services.UserService;

import java.util.ArrayList;
import java.util.List;

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
    public String adminPanel(Model model, @AuthenticationPrincipal User admin) {
        model.addAttribute("admin", admin);

        model.addAttribute("newUser", new User());
        model.addAttribute("allRoles", roleService.getAllRoles());

        model.addAttribute("users", userService.getAllUsers());
        return "admin_panel";
    }

    @PostMapping("/user/new")
    public String createNewUser(@ModelAttribute("user") User user,
                                @RequestParam(value = "identifiers") int identifiers) {
        List<Role> roleList = new ArrayList<>();
        if (identifiers == 1) {
            roleList = roleService.getAllRoles();
        } else {
            roleList.add(roleService.getAllRoles().get(1));
        }
        user.setRoles(roleList);

        userService.saveUser(user);

        return "redirect:/admin";
    }

    //Update User
    @PatchMapping("/user/{id}/edit")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "identifiers") int identifiers) {
        List<Role> roleList = new ArrayList<>();
        if (identifiers == 1) {
            roleList = roleService.getAllRoles();
        } else {
            roleList.add(roleService.getAllRoles().get(1));
        }
        user.setRoles(roleList);

        userService.updateUser(user.getId(), user);
        return "redirect:/admin";
    }

    //Delete User
    @DeleteMapping("/user/{id}")
    public String deleteUser(@ModelAttribute("user") User user) {
        userService.removeUser(user.getId());
        return "redirect:/admin";
    }
}
