package com.springboot.D5_redis;

import com.springboot.D2.User;
import com.springboot.D4.UserService;
import com.springboot.dao.TestUserDaoImpl;
import com.springboot.dao.TestUserExample;
import com.springboot.domain.TestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/D5")     // 通过这里配置使下面的映射都在/users下，可去除
public class UserRedisControllers {

    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    @Autowired
    @Qualifier("userService")
    private UserService userService;        //注解为null  待研究

    @Autowired
    private TestUserDaoImpl userDaoImpl;

    @RequestMapping(value = "/userinfos", method = RequestMethod.GET)
    public List<TestUser> getUserList() {
        List<String> r = userService.getAllUsersInfo();
        List<TestUser> users = userDaoImpl.selectByExample(new TestUserExample());
        return users;
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public String postUser(@RequestBody User user) {
        // userService = (UserService) SpringBeanUtil.getBean("userService");
        userService.create(user.getName(), user.getAge());
        redisTemplate.opsForValue().set(user.getName(), user);
        return "success";
    }

    @RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
    public User getUser(@PathVariable String name) {
        User user = redisTemplate.opsForValue().get(name);
        return user;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @ModelAttribute User user) {
        User u = userService.getUser(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        userService.updateUser(user);
        redisTemplate.opsForValue().set(user.getName(), user);
        return "success";
    }

    @RequestMapping(value = "/user/{name}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable String name) {
        userService.deleteByName(name);
        redisTemplate.delete(name);
        return "success";
    }


}