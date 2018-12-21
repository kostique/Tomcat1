package com.coreteka.service.impl;

import com.coreteka.dao.UserDAO;
import com.coreteka.dao.impl.UserDAOImpl;
import com.coreteka.entities.User;
import com.coreteka.exceptions.UserAlreadyExistsException;
import com.coreteka.exceptions.UserCouldNotBeFoundException;
import com.coreteka.service.UserService;
import com.coreteka.util.PersistenceUtil;

import java.util.List;


public class UserServiceImpl implements UserService {

    @Override
    public User create(User user) {

        UserDAO userDAO = new UserDAOImpl();

        if(userDAO.isUserExist(user.getUsername())){
            throw new UserAlreadyExistsException("User already exists.");
        }

        PersistenceUtil.beginTransaction();

        User createdUser = userDAO.create(user);

        PersistenceUtil.commitTransaction();

        return createdUser;
    }

    @Override
    public User getByUsername(String username){

        UserDAO userDAO = new UserDAOImpl();

        if (!userDAO.isUserExist(username)) {
            throw new UserCouldNotBeFoundException("User couldn't be found");
        }
        User user = userDAO.getByUsername(username);
        return user;
    }

    @Override
    public List<User> getAll(){

        UserDAO userDAO = new UserDAOImpl();

        List<User> allUsers = userDAO.getAllUsers();

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

        PersistenceUtil.commitTransaction();
    }
}
