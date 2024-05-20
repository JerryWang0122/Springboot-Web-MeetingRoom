package com.example.demo.dao;

import com.example.demo.model.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<User> findAllUsers() {
        String sql = "select id, name from user";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public Optional<User> getUser(Integer id) {
        String sql = "select id, name from user where id = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
            return Optional.of(user);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Optional.of(null);
    }
}
