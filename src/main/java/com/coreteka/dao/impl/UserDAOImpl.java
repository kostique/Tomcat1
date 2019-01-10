package com.coreteka.dao.impl;

import com.coreteka.dao.UserDAO;
import com.coreteka.entities.User;
import com.coreteka.util.PersistenceUtil;

import javax.persistence.*;
import java.util.List;


public class UserDAOImpl implements UserDAO {

    @Override
    public User getById(long id){
        return PersistenceUtil.getEntityManager().find(User.class, id);
    }

    @Override
    public User saveUser(User user) {
        return PersistenceUtil.getEntityManager().merge(user);
    }

    @Override
    public List<User> getAll() {
        TypedQuery<User> query = PersistenceUtil.getEntityManager().createQuery("FROM User u", User.class);
        return query.getResultList();
    }

    @Override
    public User getByLogin(String login) {
        User existingUser;
            TypedQuery<User> query = PersistenceUtil.getEntityManager().
                    createQuery("SELECT u FROM User u WHERE u.login = :login", User.class);
            existingUser = query.setParameter("login", login).getSingleResult();
        return existingUser;
    }

    @Override
    public void delete(long id) {
        Query query = PersistenceUtil.getEntityManager()
                .createQuery("DELETE FROM User u WHERE u.id = :id");
        query.setParameter("id", id).executeUpdate();
    }

    @Override
    public boolean isUserExist(String columnName, String value){
        TypedQuery<Long> query = PersistenceUtil.
                getEntityManager().
                createQuery("SELECT COUNT(u) FROM User u WHERE u." + columnName + " = :" + columnName, Long.class);
        query.setParameter(columnName, value);
        return (query.getSingleResult() != 0L);
    }

    @Override
    public boolean isUserExist(String columnName, long value){
        TypedQuery<Long> query = PersistenceUtil.
                getEntityManager().
                createQuery("SELECT COUNT(u) FROM User u WHERE u." + columnName + " = :" + columnName, Long.class);
        query.setParameter(columnName, value);
        return (query.getSingleResult() != 0L);
    }

    @Override
    public boolean isUserExist(String columnName, String value, long id){
        String qlString = "SELECT COUNT(u) FROM User u WHERE u." + columnName + " = :" + columnName + " AND u.id != :id";
        TypedQuery<Long> query = PersistenceUtil.
                getEntityManager().
                createQuery(qlString, Long.class);
        query.setParameter(columnName, value);
        query.setParameter("id", id);
        return (query.getSingleResult() != 0L);
    }
}