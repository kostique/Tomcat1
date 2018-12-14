package com.coreteka.service.impl;

import com.coreteka.dao.UserDAO;
import com.coreteka.dao.impl.UserDAOImpl;
import com.coreteka.entities.User;
import com.coreteka.service.UserService;
import com.coreteka.util.PersistenceUtil;


public class UserServiceImpl implements UserService {

    @Override
    public User create(User user) {

        UserDAO userDAO = new UserDAOImpl();

        PersistenceUtil.beginTransaction();

        user = userDAO.create(user);

        PersistenceUtil.commitTransaction();

        return user;
    }

    @Override
    public User getByUsername(String username){

        UserDAO userDAO = new UserDAOImpl();

        PersistenceUtil.beginTransaction();

        User user = userDAO.getByUsername(username);

        PersistenceUtil.commitTransaction();

        return user;
    }

    @Override
    public User update(User user) {

        UserDAO userDAO = new UserDAOImpl();

        PersistenceUtil.beginTransaction();

        User updatedUser = userDAO.update(user);

        PersistenceUtil.commitTransaction();

        return updatedUser;
    }
}
