package com.coreteka.service.impl;

import com.coreteka.dao.UserDAO;
import com.coreteka.dao.impl.UserDAOImpl;
import com.coreteka.entities.User;
import com.coreteka.exceptions.*;
import com.coreteka.service.UserService;
import com.coreteka.util.PersistenceUtil;
import com.coreteka.util.ValidationUtil;
import java.util.List;


public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public User create(User user) {
        validateUserBeforeCreate(user);
        PersistenceUtil.beginTransaction();
        User createdUser = userDAO.saveUser(user);
        PersistenceUtil.commitTransaction();
        return createdUser;
    }

    @Override
    public User getByLogin(String login){
        if (!userDAO.isUserExist("login", login)){
            throw new UserNotFoundException("User not found.");
        }
        return userDAO.getByLogin(login);
    }

    @Override
    public List<User> getAll(){
        return userDAO.getAll();
    }

    @Override
    public User update(User user) {
        validateUserBeforeUpdate(user);
        User existingUser = userDAO.getById(user.getId());
        copyUpdatingFields(user, existingUser);

        PersistenceUtil.beginTransaction();
        User updatedUser = userDAO.saveUser(user);
        PersistenceUtil.commitTransaction();

        return updatedUser;
    }

    @Override
    public void delete(long id) {
        if (!userDAO.isUserExist("id", id)) {
            throw new UserNotFoundException("User not found.");
        }
        PersistenceUtil.beginTransaction();
        userDAO.delete(id);
        PersistenceUtil.commitTransaction();
    }

    private void validateUserBeforeCreate(User user){
        ValidationUtil.validateEntityConstraints(user);

        if (userDAO.isUserExist("email", user.getEmail())){
            throw new DuplicateUserAttributeValueException("Email already exists.");
        }
        if (userDAO.isUserExist("login", user.getLogin())){
            throw new DuplicateUserAttributeValueException("Login already exists.");
        }
    }

    private void validateUserBeforeUpdate(User user){
        ValidationUtil.validateEntityConstraints(user);

        long id = user.getId();
        if (!userDAO.isUserExist("id", user.getId())){
            throw new UserNotFoundException("User not found.");
        }
        if (userDAO.isUserExist("email", user.getEmail(), id)){
            throw new DuplicateUserAttributeValueException("Email already exists.");
        }
    }

    private void copyUpdatingFields(User user, User existingUser){
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setEmail(user.getEmail());
    }
}