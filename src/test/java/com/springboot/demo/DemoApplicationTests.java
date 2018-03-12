package com.springboot.demo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
	private DemoBeanProperties demoBeanProperties;

    private MockMvc mvc;


	@Before()  //这个方法在每个方法执行之前都会执行一遍
	public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();    //注入webApplication容器
//        mvc = MockMvcBuilders.standaloneSetup(new DemoApplication()).build();
	}

	@Test
	public void contextLoads() throws Exception {
		System.out.println(demoBeanProperties.getName());
		System.out.println(demoBeanProperties.getDesc());
		Assert.assertEquals(demoBeanProperties.getName(), "miachelyin");
		Assert.assertEquals(demoBeanProperties.getTitle(),"values");
//        MvcResult result =mvc.perform(MockMvcRequestBuilders.get("/hello")  //执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())  //andExpect 添加ResultMatcher验证规则，验证控制器执行完成后结果是否正确；
//                .andDo(print())             //添加ResultHandler结果处理器，比如调试时打印结果到控制台；
//                .andReturn();
	}

}
