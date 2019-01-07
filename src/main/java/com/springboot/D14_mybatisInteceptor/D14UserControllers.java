package com.springboot.D14_mybatisInteceptor;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.annotation.MyLog;
import com.springboot.dao.TestUserDaoImpl;
import com.springboot.dao.TestUserExample;
import com.springboot.domain.City;
import com.springboot.domain.TestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/D14")     // 通过这里配置使下面的映射都在/users下，可去除
public class D14UserControllers {



    @Autowired
    private TestUserDaoImpl userDaoImpl;

    @MyLog
    @RequestMapping(value = "/userinfos", method = RequestMethod.GET)
    public PageInfo getUserList() {
        PageHelper.startPage(1,4);
        List<TestUser> users = userDaoImpl.queryUsers(new TestUserExample());
        PageInfo pageInfo = new PageInfo(users,4);

        Optional.of(new City())
//                .map(City :: getId)
                .map(City :: getProvinceId)
                .ifPresent(System.out::println);
        return pageInfo;
    }

}