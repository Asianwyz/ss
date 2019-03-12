package com.asia.blog.service;

import com.asia.blog.po.User;

public interface UserService {

    User checkUser(String username, String password);
}
