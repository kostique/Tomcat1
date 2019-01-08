package com.coreteka.dao;

import com.coreteka.entities.User;

import java.util.List;

public interface UserDAO {
    //save
    User saveUser(User user);

    //read
    List<User> getAll();

    User getByUsername(String username);

    User getById(long id);

    //delete
    void delete(long id);

    boolean isUserExist(String username);

    boolean isUserExist(long id);

}
