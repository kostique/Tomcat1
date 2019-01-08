package com.coreteka.dao.impl;

import com.coreteka.dao.UserDAO;
import com.coreteka.entities.User;
import com.coreteka.exceptions.DuplicateUserAttributeValueException;
import com.coreteka.exceptions.NullUserAttributeException;
import com.coreteka.exceptions.UserNotFoundException;
import com.coreteka.util.PersistenceUtil;


import javax.persistence.*;
import javax.validation.ConstraintViolationException;
import java.util.List;


public class UserDAOImpl implements UserDAO {

    @Override
    public User getById(long id){
        return PersistenceUtil.getEntityManager().find(User.class, id);
    }

    @Override
    public User saveUser(User user) {
        User savedUser;
        try {
            savedUser = PersistenceUtil.getEntityManager().merge(user);
        } catch (PersistenceException e) {
            PersistenceUtil.rollbackTransaction();
            throw new DuplicateUserAttributeValueException("Duplicate user attribute value found.");
        } catch (ConstraintViolationException e){
            PersistenceUtil.rollbackTransaction();
            throw new NullUserAttributeException("Null user attribute found.");
        }
        return savedUser;
    }

    @Override
    public List<User> getAll() {
        TypedQuery<User> query = PersistenceUtil.getEntityManager().createQuery("FROM User u", User.class);
        return query.getResultList();
    }

    @Override
    public User getByLogin(String login) {
        User existingUser;
        try {
            TypedQuery<User> query = PersistenceUtil.getEntityManager().
                    createQuery("SELECT u FROM User u WHERE u.login = :login", User.class);
            existingUser = query.setParameter("login", login).getSingleResult();
        } catch (NoResultException e) {
            throw new UserNotFoundException("User not found.");
        }
        return existingUser;
    }

    @Override
    public void delete(long id) {
        Query query = PersistenceUtil.getEntityManager()
                .createQuery("DELETE FROM User u WHERE u.id = :id");
        query.setParameter("id", id).executeUpdate();
    }

    public boolean isUserExist(long id) {
        TypedQuery<Long> query = PersistenceUtil.
                getEntityManager().
                createQuery("SELECT COUNT(u) FROM User u WHERE u.id = :id", Long.class);
        query.setParameter("id", id);
        return (query.getSingleResult() != 0L);
    }

    public boolean isUserExist(String login){
        TypedQuery<Long> query = PersistenceUtil.
                getEntityManager().
                createQuery("SELECT COUNT(u) FROM User u WHERE u.login = :login", Long.class);
        query.setParameter("login", login);
        return (query.getSingleResult() != 0L);
    }
}