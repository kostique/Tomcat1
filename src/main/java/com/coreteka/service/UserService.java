package com.coreteka.service;

import com.coreteka.entities.User;

import java.util.List;

public interface UserService {

    User create(User user);

    User getByLogin(String login);

    List<User> getAll();

    User update(User user);

    void delete(long id);

}
