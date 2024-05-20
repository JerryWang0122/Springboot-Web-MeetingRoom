package com.example.demo.dao;

import com.example.demo.model.po.User;

import java.util.List;

public interface UserDao {
    List<User> findAllUsers();
    User getUser(Integer id);
}
