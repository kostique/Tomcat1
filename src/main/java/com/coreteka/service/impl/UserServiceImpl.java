package com.coreteka.service.impl;

import com.coreteka.dao.UserDAO;
import com.coreteka.dao.impl.UserDAOImpl;
import com.coreteka.entities.User;
import com.coreteka.exceptions.*;
import com.coreteka.service.UserService;
import com.coreteka.util.PersistenceUtil;
import java.util.List;


public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public User create(User user) {
        if(!isUserValidForCreating(user)){
            throw new InvalidUserAttributeValueException("Invalid user attribute value found.");
        }
        PersistenceUtil.beginTransaction();
        User createdUser = userDAO.saveUser(user);
        PersistenceUtil.commitTransaction();
        return createdUser;
    }

    @Override
    public User getByLogin(String login){
        if (!userDAO.isEntryExist("login", login)){
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
        isUserValidForUpdating(user);
        User updatedUser;
        PersistenceUtil.beginTransaction();
        updatedUser = userDAO.saveUser(user);
        PersistenceUtil.commitTransaction();
        return updatedUser;
    }

    @Override
    public void delete(long id) {
        if (!userDAO.isEntryExist("id", id)) {
            throw new UserNotFoundException("User not found.");
        }
        PersistenceUtil.beginTransaction();
        userDAO.delete(id);
        PersistenceUtil.commitTransaction();
    }

    private boolean isUserValidForCreating(User user){
        return (!userDAO.isEntryExist("id", user.getId())
                && !isUserContainNullAttributeValue(user)
                && !userDAO.isEntryExist("login", user.getLogin())
                && !userDAO.isEntryExist("email", user.getEmail()));
    }

//    private boolean isUserValidForUpdating(User user){
//        long id = user.getId();
//        return (userDAO.isEntryExist("id", user.getId())
//                && !isUserContainNullAttributeValue(user)
//                && !userDAO.isEntryExist("login", user.getLogin(), id)
//                && !userDAO.isEntryExist("email", user.getEmail(), id));
//    }

    private void isUserValidForUpdating (User user){
        long id = user.getId();
        if (userDAO.isEntryExist("id", user.getId())){
            throw new UserNotFoundException("User not found.");
        }
        if (!isUserContainNullAttributeValue(user)){
            throw new NullUserAttributeValueException("Null user attribute found.");
        }
        if (userDAO.isEntryExist("login", user.getLogin(), id)){
            throw new DuplicateUserAttributeValueException("Duplicate user attribute value found.");
        }
        if (userDAO.isEntryExist("email", user.getEmail(), id)){
            throw new DuplicateUserAttributeValueException("Duplicate user attribute value found.");
        }
    }

    private boolean isUserContainNullAttributeValue(User requestUser){
        return requestUser.getLogin() == null
                || requestUser.getPassword() == null
                || requestUser.getEmail() == null
                || requestUser.getUsername() == null;
    }

}
