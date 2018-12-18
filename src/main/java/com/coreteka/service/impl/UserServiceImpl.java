package com.coreteka.service.impl;

import com.coreteka.dao.UserDAO;
import com.coreteka.dao.impl.UserDAOImpl;
import com.coreteka.entities.User;
import com.coreteka.service.UserService;
import com.coreteka.util.PersistenceUtil;

import java.util.List;


public class UserServiceImpl implements UserService {

    @Override
    public void create(User user) {

        UserDAO userDAO = new UserDAOImpl();

        PersistenceUtil.beginTransaction();

        userDAO.create(user);

        PersistenceUtil.commitTransaction();
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
    public List<User> getAll(){

        UserDAO userDAO = new UserDAOImpl();

        PersistenceUtil.beginTransaction();

        List<User> allUsers = userDAO.getAllUsers();

        PersistenceUtil.commitTransaction();

        return allUsers;
    }

    @Override
    public User update(User user) {

        UserDAO userDAO = new UserDAOImpl();

        PersistenceUtil.beginTransaction();

        User updatedUser = userDAO.update(user);

        PersistenceUtil.commitTransaction();

        return updatedUser;
    }

    @Override
    public void delete(String username){

        UserDAO userDAO = new UserDAOImpl();

        PersistenceUtil.beginTransaction();

        User existedUser = getByUsername(username);

        userDAO.delete(existedUser);

        PersistenceUtil.commitTransaction();
    }
}
