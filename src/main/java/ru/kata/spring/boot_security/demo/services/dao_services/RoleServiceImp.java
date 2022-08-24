package ru.kata.spring.boot_security.demo.services.dao_services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {
    private final RoleDao roleDao;

    public RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleById(Integer id) {
        return roleDao.getRoleById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {
        return roleDao.getALlRoles();
    }

    @Override
    @Transactional
    public void saveRole(Role role) {
        roleDao.saveRole(role);
    }
}
