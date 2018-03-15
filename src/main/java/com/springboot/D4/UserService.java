package com.springboot.D4;

import com.springboot.D2.User;

import java.util.List;

public interface UserService {

    /**
     * 新增一个用户
     *
     * @param name
     * @param age
     */
    void create(String name, Integer age);

    /**
     * 根据name删除一个用户高
     *
     * @param name
     */
    void deleteByName(String name);

    /**
     * 获取用户总量
     */
    Integer getAllUsers();

    /**
     * 删除所有用户
     */
    void deleteAllUsers();

    /**
     * 获取所有的用户名称
     */
    List<String> getAllUsersInfo();

    /**
     * 获取单个用户
     * @param id 用户id
     * @return
     */
    User getUser(Long id);

    /**
     * 更新用户
     * @param user 用户
     */
    void updateUser(User user);

    /**
     * 删除指定用户
     * @param id 用户id
     */
    void deleteById(Long id);
}