package com.coreteka.dao.impl;

import com.coreteka.entities.User;
import com.coreteka.dao.UserDAO;
import com.coreteka.util.PersistenceUtil;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public void create(User user) {
        PersistenceUtil.getEntityManager().persist(user);
    }

    @Override
    public List<User> getAllUsers(){
        TypedQuery<User> query = PersistenceUtil.getEntityManager().createQuery("FROM User a", User.class);
        return query.getResultList();
    }

    @Override
    public User getByUsername(String username){
        TypedQuery<User> query = PersistenceUtil.getEntityManager().
                createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        return query.setParameter("username", username).getSingleResult();
    }

    @Override
    public User update(User user) {
        return PersistenceUtil.getEntityManager().merge(user);
    }

    @Override
    public void delete(User user){
        PersistenceUtil.getEntityManager().remove(user);
    }
}
