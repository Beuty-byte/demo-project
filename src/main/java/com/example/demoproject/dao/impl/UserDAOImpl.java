package com.example.demoproject.dao.impl;

import com.example.demoproject.dao.UserDAO;
import com.example.demoproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class UserDAOImpl implements UserDAO {

    private final EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public long save(User user) {
        entityManager.persist(user);
        entityManager.flush();
        return user.getId();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return entityManager.createQuery("from User where email = :email", User.class)
                .setParameter("email", email)
                .getResultStream()
                .findAny();
    }

    @Override
    public List<User> findByName(Integer amountUsers, Integer page, String param) {
        return entityManager.createQuery("from User where name like :param", User.class)
                .setParameter("param", "%" + param + "%")
                .setFirstResult(amountUsers * page)
                .setMaxResults(amountUsers)
                .getResultList();
    }

    @Override
    public List<User> findByBirthday(Integer amountUsers, Integer page, String param) {
        return entityManager.createQuery("from User where dateOfBirthday = :param and dataOfBirthday > :param", User.class)
                .setParameter("param", param)
                .setFirstResult(amountUsers * page)
                .setMaxResults(amountUsers)
                .getResultList();
    }
}