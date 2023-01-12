package com.example.demoproject.dao.impl;

import com.example.demoproject.dao.EmailDAO;
import com.example.demoproject.model.Email;
import com.example.demoproject.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class EmailDAOImpl implements EmailDAO {

    private final EntityManager entityManager;

    @Autowired
    public EmailDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Email> find(String email) {
        return entityManager.createQuery("from Email where email = :email", Email.class)
                .setParameter("email", email)
                .getResultStream()
                .findAny();
    }

    @Override
    public long save(Email email) {
        entityManager.persist(email);
        entityManager.flush();
        return email.getId();
    }

    @Override
    public long update(Email email) {
        entityManager.merge(email);
        entityManager.flush();
        return email.getId();
    }

    @Override
    public void delete(Email email) {
        entityManager.remove(email);
    }

    @Override
    public Optional<Email> findByEmail(String param) {
        return entityManager.createQuery("from Email where email = :email", Email.class)
                .setParameter("email", param)
                .getResultStream()
                .findAny();
    }
}
