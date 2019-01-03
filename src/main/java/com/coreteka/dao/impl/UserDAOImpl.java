package com.coreteka.dao.impl;

import com.coreteka.dao.UserDAO;
import com.coreteka.entities.User;
import com.coreteka.exceptions.InvalidUserAttributesException;
import com.coreteka.util.PersistenceUtil;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;


public class UserDAOImpl implements UserDAO {

    @Override
    public User create(User user) {
        User createdUser;
        try {
            createdUser = PersistenceUtil.getEntityManager().merge(user);
        } catch (PersistenceException e) {
            PersistenceUtil.rollbackTransaction();
            throw new InvalidUserAttributesException("Invalid user attributes");
        }
        return createdUser;
    }

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = PersistenceUtil.getEntityManager().createQuery("FROM User a", User.class);
        return query.getResultList();
    }

    @Override
    public User getByUsername(String username) {
            TypedQuery<User> query = PersistenceUtil.getEntityManager().
                    createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
            return query.setParameter("username", username).getSingleResult();
    }

    @Override
    public User update(User user) {
        User updatedUser;
        try{
            updatedUser = PersistenceUtil.getEntityManager().merge(user);
        }catch (PersistenceException e) {
            PersistenceUtil.rollbackTransaction();
            throw new InvalidUserAttributesException("Invalid user attributes");
        }
        return updatedUser;
    }

    @Override
    public void delete(String username) {
        Query query = PersistenceUtil.getEntityManager()
                .createQuery("DELETE FROM User u WHERE u.username = :username");
        query.setParameter("username", username).executeUpdate();

    }

    public boolean isUserExist(String username) {
        TypedQuery<Long> query = PersistenceUtil.getEntityManager()
                .createQuery("SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class);
        query.setParameter("username", username);

        /*if query contains zero users, it means that DB has no user with
         corresponded username thus method returns false
          */
        return (query.getSingleResult() != 0L);
    }
}
