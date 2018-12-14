package com.coreteka.dao;

import com.coreteka.entities.User;

public interface UserDAO {
    //create
    User create(User user);

    User getById(long id);

    User getByUsername(String username);

    //update
    User update(User user);
}
