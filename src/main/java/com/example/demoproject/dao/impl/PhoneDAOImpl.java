package com.example.demoproject.dao.impl;

import com.example.demoproject.dao.PhoneDAO;
import com.example.demoproject.model.Phone;
import com.example.demoproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class PhoneDAOImpl implements PhoneDAO {

    private final EntityManager entityManager;

    @Autowired
    public PhoneDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Phone> find(String phone) {
        return entityManager.createQuery("from Phone where phone = :phone", Phone.class)
                .setParameter("phone", phone)
                .getResultStream()
                .findAny();
    }

    @Override
    public long save(Phone phone) {
        entityManager.persist(phone);
        entityManager.flush();
        return phone.getId();
    }

    @Override
    public long update(Phone phone) {
        entityManager.merge(phone);
        entityManager.flush();
        return phone.getId();
    }

    @Override
    public void delete(Phone phone) {
        entityManager.remove(phone);
    }

    @Override
    public Optional<Phone> findByNumber(String param) {
        return entityManager.createQuery("from Phone where phoneNumber = :phoneNumber", Phone.class)
                .setParameter("phoneNumber", param)
                .getResultStream()
                .findAny();
    }
}
