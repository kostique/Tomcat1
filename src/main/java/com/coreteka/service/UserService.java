package com.coreteka.service;

import com.coreteka.entities.User;

public interface UserService {
    User create(User user);

    User getByUsername(String name);

    User update(User user);
}
