package com.coreteka.dao;

import com.coreteka.entities.User;

import java.util.List;

public interface UserDAO {
    //save
    User saveUser(User user);

    //read
    List<User> getAll();

    User getByLogin(String login);

    User getById(long id);

    //delete
    void delete(long id);

    boolean isUserExist(String columnName, String value);

    boolean isUserExist(String columnName, long value);

    boolean isUserExist(String columnName, String value, long id);

}
