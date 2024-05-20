package com.example.demo.dao;

import com.example.demo.model.po.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> findAllUsers();
    Optional<User> getUser(Integer id);
}
