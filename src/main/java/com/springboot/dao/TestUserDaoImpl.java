package com.springboot.dao;

import com.springboot.domain.TestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class TestUserDaoImpl {

    @Autowired
    private TestUserDao userDao;

    
    public int deleteByPrimaryKey(Integer id) {
        return userDao.deleteByPrimaryKey(id);
    }

    
    public int insert(TestUser record) {
        return userDao.insert(record);
    }

    
    public int insertSelective(TestUser record) {
        return userDao.insertSelective(record);
    }

    
    public List<TestUser> selectByExample(TestUserExample example) {
        return userDao.selectByExample(example);
    }

    
    public TestUser selectByPrimaryKey(Integer id) {
        return userDao.selectByPrimaryKey(id);
    }

    
    public int updateByExampleSelective(TestUser record, TestUserExample example) {
        return userDao.updateByExampleSelective(record,example);
    }

    
    public int updateByExample(TestUser record, TestUserExample example) {
        return 0;
    }

    
    public int updateByPrimaryKeySelective(TestUser record) {
        return 0;
    }

    
    public int updateByPrimaryKey(TestUser record) {
        return 0;
    }
}
