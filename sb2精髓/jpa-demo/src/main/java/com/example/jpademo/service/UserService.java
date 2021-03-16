package com.example.jpademo.service;

import com.example.jpademo.entity.User;

import java.util.List;

public interface UserService {

    public Integer addUser(User user);

    public List<User> getAllUser(int page, int size);

}
