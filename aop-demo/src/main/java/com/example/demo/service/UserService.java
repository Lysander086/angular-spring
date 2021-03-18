package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

public interface UserService {

    public Integer addUser(User user);

    public List<User> getAllUser(int page, int size);

}
