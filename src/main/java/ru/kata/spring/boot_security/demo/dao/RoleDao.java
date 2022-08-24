package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

public interface RoleDao {
    Role getRoleById(Integer id);

    List<Role> getALlRoles();

    void saveRole(Role role);
}
