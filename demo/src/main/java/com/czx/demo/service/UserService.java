package com.czx.demo.service;

import com.czx.demo.entry.User;

public interface UserService  {

    User getUserById(Integer id);

    int insertUser(User user);
}
