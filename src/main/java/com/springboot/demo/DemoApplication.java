package com.springboot.demo;


import com.springboot.annotation.MyLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@SpringBootApplication
public class DemoApplication {

	/**
	 * @Controller：修饰class，用来创建处理http请求的对象
	 * @RestController：Spring4之后加入的注解，原来在@Controller中返回json需要@ResponseBody来配合，
	 * @RestController = @Controller + @ResponseBody  可见DemoApplicationController
	 * @RequestMapping：配置url映射
	 */

	@RequestMapping("/hello")
	@MyLog(level = "warn")
	String index() {
		System.out.println(desc);
		return "Hello Spring Boot";
	}

	@Value("${com.springboot.desc}")
	private String desc;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
