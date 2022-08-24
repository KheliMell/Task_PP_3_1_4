package ru.kata.spring.boot_security.demo.services.dao_services;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

public interface RoleService {
    Role getRoleById(Integer id);

    List<Role> getAllRoles();

    void saveRole(Role role);
}
