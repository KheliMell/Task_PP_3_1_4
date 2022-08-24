package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.access.annotation.Secured;
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
@Secured("ROLE_ADMIN") //В случае ошибки (если пропустит обычного пользователя) перехватит и не предоставит доступ
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String adminPanel(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin_panel";
    }

    @GetMapping("/user/{id}")
    public String showUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "userById";
    }

    //Create User
    @GetMapping("/user/new")
    public String newUser(Model model) {
        model.addAttribute("allRoles", roleService.getAllRoles());
        model.addAttribute("rolesForNewUser", new LocalVariableString());
        model.addAttribute("user", new User());

        return "createUser";
    }

    @PostMapping("/user/new")
    public String createNewUser(@ModelAttribute("user") User user,
                                @ModelAttribute("rolesForNewUser") LocalVariableString rolesForNewUser) {
        List<Role> roleList = new ArrayList<>();
        if (rolesForNewUser.getLocalVariable().equals("ROLE_ADMIN")) {
            roleList = roleService.getAllRoles();
        } else {
            roleList.add(roleService.getAllRoles().get(1));
        }
        user.setRoles(roleList);

        userService.saveUser(user);

        return "redirect:/admin";
    }

    //Update User
    @GetMapping("/user/{id}/edit")
    public String editUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "editUser";
    }

    @PatchMapping("/user/{id}/edit")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    //Delete User
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }


    //LocalVariable for method createNewUser
    @SuppressWarnings("unused")
    private class LocalVariableString {
        private String localVariable;

        public String getLocalVariable() {
            return localVariable;
        }

        public void setLocalVariable(String localVariable) {
            this.localVariable = localVariable;
        }
    }
}
