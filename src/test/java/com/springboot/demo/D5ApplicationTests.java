package com.springboot.demo;

import com.springboot.D2.User;
import com.springboot.D4.UserService;
import com.springboot.annotation.MyLogAspect;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class D5ApplicationTests {

    public static final Logger LOGGER = LoggerFactory.getLogger(MyLogAspect.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    private MockMvc mvc;

    @Autowired
    private UserService userSerivce;

    @Before
    public void setUp() throws Exception {
        //mvc = MockMvcBuilders.standaloneSetup(new UserRedisControllers()).build();
    }

    @Test
    public void test() throws Exception {
        //Redis 测试
        // 保存字符串
        //userSerivce.create("a", 1);

        stringRedisTemplate.opsForValue().set("aaa", "111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
        // 测试UserController
        RequestBuilder request = null;

        LOGGER.info("post 提交一个新用户");
        //post 提交一个新用户
        request = post("/D5/adduser")
                .param("name", "mia")
                .param("age", "20");
        MvcResult result = mvc.perform(request)
                .andExpect(content().string(equalTo("success")))
                .andReturn();
        LOGGER.info("post /D5/adduser:{}" + result.getResponse().getContentAsString());

        LOGGER.info("get 获取所有用户");
        //get 获取所有用户
        request = get("/D5/userinfos");
        result = mvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
        LOGGER.info("get /D5/userinfos:{}" + result.getResponse().getContentAsString());


        LOGGER.info("get 从缓存中获取用户");
        //get 从缓存中获取用户
        request = get("/D5/user/mia");
        result = mvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
        LOGGER.info("get /D5/user/mia:{}" + result.getResponse().getContentAsString());

        LOGGER.info("put 变更用户年龄");
        //put 变更用户年龄
        request = put("/D5/user/5")
                .param("name", "mia")
                .param("age", "25");
        result = mvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
        LOGGER.info("put /D5/user/5:{}" + result.getResponse().getContentAsString());

        LOGGER.info("delete 删除用户");
        //删除用户
        request = delete("/D5/user/mia");
        result = mvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
        LOGGER.info("delete /D5/user/mia:{}" + result.getResponse().getContentAsString());
        // 保存对象
        Assert.assertEquals(20, redisTemplate.opsForValue().get("mia").getAge().longValue());
    }

}
