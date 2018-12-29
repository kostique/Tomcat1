package com.coreteka.service.impl;

import com.coreteka.dao.UserDAO;
import com.coreteka.dao.impl.UserDAOImpl;
import com.coreteka.entities.User;
import com.coreteka.exceptions.InvalidUserAttributesException;
import com.coreteka.exceptions.UserAlreadyExistsException;
import com.coreteka.exceptions.UserNotFoundException;
import com.coreteka.service.UserService;
import com.coreteka.util.PersistenceUtil;

import javax.persistence.PersistenceException;
import java.util.List;


public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public User create(User user) {
        userDAO = new UserDAOImpl();
        if(userDAO.isUserExist(user.getUsername())){
            throw new UserAlreadyExistsException("User already exists.");
        }
        PersistenceUtil.beginTransaction();
        User createdUser;
        createdUser = userDAO.create(user);
        PersistenceUtil.commitTransaction();
        return createdUser;
    }

    @Override
    public User getByUsername(String username){
        userDAO = new UserDAOImpl();
        if (!userDAO.isUserExist(username)) {
            throw new UserNotFoundException("User couldn't be found");
        }
        return userDAO.getByUsername(username);
    }

    @Override
    public List<User> getAll(){
        userDAO = new UserDAOImpl();
        return userDAO.getAllUsers();
    }

    @Override
    public User update(User user) {
        userDAO = new UserDAOImpl();
        PersistenceUtil.beginTransaction();
        User updatedUser = userDAO.update(user);

        try{
            PersistenceUtil.commitTransaction();
        }catch (PersistenceException e) {
            PersistenceUtil.rollbackTransaction();
            throw new InvalidUserAttributesException("Invalid user attributes");
        }
        return updatedUser;
    }

    @Override
    public void delete(String username){
        userDAO = new UserDAOImpl();
        if (!userDAO.isUserExist(username)) {
            throw new UserNotFoundException("User couldn't be found");
        }
        PersistenceUtil.beginTransaction();
        userDAO.delete(username);
        PersistenceUtil.commitTransaction();
    }
}
