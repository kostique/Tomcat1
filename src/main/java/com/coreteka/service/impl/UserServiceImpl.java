package com.coreteka.service.impl;

import com.coreteka.dao.UserDAO;
import com.coreteka.dao.impl.UserDAOImpl;
import com.coreteka.entities.User;
import com.coreteka.exceptions.*;
import com.coreteka.service.UserService;
import com.coreteka.util.PersistenceUtil;

import javax.persistence.PersistenceException;
import java.util.List;


public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public User create(User user) {
        if(userDAO.isUserExist(user.getUsername())){
            throw new UserAlreadyExistsException("User already exists.");
        }
        PersistenceUtil.beginTransaction();
        User createdUser = userDAO.saveUser(user);
        PersistenceUtil.commitTransaction();
        return createdUser;
    }

    @Override
    public User getByLogin(String login){
        return userDAO.getByLogin(login);
    }

    @Override
    public List<User> getAll(){
        return userDAO.getAll();
    }

    @Override
    public User update(User user) {
        if (!userDAO.isUserExist(user.getId())) {
            throw new UserNotFoundException("User not found.");
        }
        User updatedUser;
        PersistenceUtil.beginTransaction();
        try {
            updatedUser = userDAO.saveUser(user);
            PersistenceUtil.commitTransaction();
        } catch (PersistenceException e) {
            PersistenceUtil.rollbackTransaction();
            throw new DuplicateOrNullUserAttributeValueException("Duplicate or null user attribute value found.");
        }
        return updatedUser;
    }

    @Override
    public void delete(long id) {
        if (!userDAO.isUserExist(id)) {
            throw new UserNotFoundException("User not found.");
        }
        PersistenceUtil.beginTransaction();
        userDAO.delete(id);
        PersistenceUtil.commitTransaction();
    }
}
