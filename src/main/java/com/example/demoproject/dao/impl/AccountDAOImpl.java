package com.example.demoproject.dao.impl;

import com.example.demoproject.dao.AccountDAO;
import com.example.demoproject.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO {

    private final EntityManager entityManager;

    @Autowired
    public AccountDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Account> getAll() {
        return entityManager.createQuery("from Account", Account.class).getResultList();
    }
}
