package com.coreteka.dao.impl;

import com.coreteka.entities.User;
import com.coreteka.dao.UserDAO;
import com.coreteka.util.PersistenceUtil;

import javax.persistence.TypedQuery;

public class UserDAOImpl implements UserDAO {

    @Override
    public User create(User user) {
        return PersistenceUtil.getEntityManager().merge(user);
    }

    @Override
    public User getById(long id) {
        return PersistenceUtil.getEntityManager().find(User.class, id);
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
}
