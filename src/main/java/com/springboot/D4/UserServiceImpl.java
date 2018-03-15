package com.springboot.D4;

import com.springboot.D2.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(String name, Integer age) {
        jdbcTemplate.update("insert into test(NAME, AGE) values(?, ?)", name, age);
    }

    @Override
    public void deleteByName(String name) {
        jdbcTemplate.update("delete from test where NAME = ?", name);
    }

    @Override
    public Integer getAllUsers() {
        return jdbcTemplate.queryForObject("select count(1) from test", Integer.class);
    }

    @Override
    public void deleteAllUsers() {
        jdbcTemplate.update("delete from test");
    }

    @Override
    public List<String> getAllUsersInfo() {
        return jdbcTemplate.queryForList("select NAME from test",String.class);
    }

    @Override
    public User getUser(Long id) {
        return jdbcTemplate.queryForObject("select * from test where ID = ?", new Object[]{id} ,User.class);
    }

    @Override
    public void updateUser(User user) {
        jdbcTemplate.update("update test set NAME = ? , AGE = ? where id =? ",user.getName(),user.getAge(),user.getId());
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("delete from test where id = ?", id);
    }
}