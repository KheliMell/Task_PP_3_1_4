package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoImp implements RoleDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Role getRoleById(Integer id) {
        return em.find(Role.class, id);
    }

    @Override
    public List<Role> getALlRoles() {
        TypedQuery<Role> query = em.createQuery("from Role", Role.class);
        return query.getResultList();
    }

    @Override
    public void saveRole(Role role) {
        em.persist(role);
    }
}
