package com.coreteka.service.impl;

import com.coreteka.dao.UserDAO;
import com.coreteka.dao.impl.UserDAOImpl;
import com.coreteka.entities.User;
import com.coreteka.exceptions.*;
import com.coreteka.service.UserService;
import com.coreteka.util.PersistenceUtil;
import com.coreteka.util.ValidationUtil;

import javax.validation.*;
import java.util.List;
import java.util.Set;


public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAOImpl();
    private StringBuffer messageBuffer = new StringBuffer();

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
        validateUserForNullFields(user);

        if (userDAO.isUserExist("email", user.getEmail())){
            throw new DuplicateUserAttributeValueException("Email already exists.");
        }
        if (userDAO.isUserExist("login", user.getLogin())){
            throw new DuplicateUserAttributeValueException("Login already exists.");
        }
    }

    private void validateUserBeforeUpdate (User user){
        validateUserForNullFields(user);
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

    private void validateUserForNullFields(User user){
        Set<ConstraintViolation<User>> constraintViolations = ValidationUtil.getValidator().validate(user);
        if(constraintViolations.size() > 0) {
            String header = "Constraint violations found at: \n";
            messageBuffer.setLength(0);
            messageBuffer.append(header);
            constraintViolations
                    .stream()
                    .map(this::composeMessage)
                    .forEach(message -> messageBuffer.append(message));
            throw new ConstraintViolationFoundException(messageBuffer.toString());
        }
    }

    private StringBuffer composeMessage(ConstraintViolation<User> userConstraintViolation){
        String propertyPath = userConstraintViolation.getPropertyPath().toString() + ": ";
        String value = userConstraintViolation.getInvalidValue() + "  ";
        String constraintMessage = "(" + userConstraintViolation.getMessage() + ")\n";
        return (new StringBuffer().append(propertyPath).append(value).append(constraintMessage));
    }
}