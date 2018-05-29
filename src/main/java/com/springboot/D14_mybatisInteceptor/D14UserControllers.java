package com.springboot.D14_mybatisInteceptor;

import com.springboot.annotation.MyLog;
import com.springboot.dao.TestUserDaoImpl;
import com.springboot.dao.TestUserExample;
import com.springboot.domain.TestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/D14")     // 通过这里配置使下面的映射都在/users下，可去除
public class D14UserControllers {



    @Autowired
    private TestUserDaoImpl userDaoImpl;

    @MyLog
    @RequestMapping(value = "/userinfos", method = RequestMethod.GET)
    public List<TestUser> getUserList() {
        List<TestUser> users = userDaoImpl.queryUsers(new TestUserExample());
        return users;
    }

}