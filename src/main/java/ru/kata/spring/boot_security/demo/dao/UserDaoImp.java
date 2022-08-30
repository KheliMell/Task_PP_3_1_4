package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void saveUser(User user) {
        em.persist(user);
    }

    @Override
    public void updateUser(Long id, User user) {
        user.setId(id);
        em.merge(user);
    }

    @Override
    public void removeUser(Long id) {
        User userForDeletion = em.find(User.class, id);
        em.remove(userForDeletion);
    }

    @Override
    public User getUserById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = em.createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public User findUserByEmail(String email) {
        String query = "select user from User user join fetch user.roles where user.email = \'" + email + "\'";
        return em.createQuery(query, User.class).getSingleResult();
    }
}
