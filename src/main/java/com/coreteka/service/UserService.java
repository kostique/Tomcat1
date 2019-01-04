package com.coreteka.service;

import com.coreteka.entities.User;

import java.util.List;

public interface UserService {

    User create(User user);
    
    User getById(long id);

    User getByUsername(String name);

    List<User> getAll();

    User update(User user);

    void delete(String username);



}
