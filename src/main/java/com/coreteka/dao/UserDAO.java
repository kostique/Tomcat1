package com.coreteka.dao;

import com.coreteka.entities.User;

import java.util.List;

public interface UserDAO {
    //create
    User create(User user);

    //read
    List<User> getAllUsers();

    User getByUsername(String username);

    //update
    User update(User user);

    //delete
    void delete(String username);

    boolean isUserExist(String username);

}
