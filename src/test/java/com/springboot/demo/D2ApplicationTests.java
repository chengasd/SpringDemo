package com.springboot.demo;

import com.springboot.D2.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class D2ApplicationTests {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test
    public void testUserController() throws Exception {
        // 测试UserController
        RequestBuilder request = null;

        // 1、get查一下user列表，应该为空
        request = get("/users/");
        MvcResult result = mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());

        // 2、post提交一个user
        request = post("/users/")
                .param("id", "1")
                .param("name", "测试大师")
                .param("age", "20");
        result = mvc.perform(request)
                .andExpect(content().string(equalTo("success")))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());

        // 3、get获取user列表，应该有刚才插入的数据
        request = get("/users/");
        result = mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"测试大师\",\"age\":20}]")))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());

        // 4、put修改id为1的user
        request = put("/users/1")
                .param("name", "测试终极大师")
                .param("age", "30");
        result = mvc.perform(request)
                .andExpect(content().string(equalTo("success")))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());

        // 5、get一个id为1的user
        request = get("/users/1");
        result = mvc.perform(request)
                .andExpect(content().string(equalTo("{\"id\":1,\"name\":\"测试终极大师\",\"age\":30}")))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());

        // 6、del删除id为1的user
        request = delete("/users/1");
        result = mvc.perform(request)
                .andExpect(content().string(equalTo("success")))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());

        // 7、get查一下user列表，应该为空
        request = get("/users/");
        result = mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());

    }
}
