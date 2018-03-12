package com.springboot.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class DemoApplicationController {

    /**
     *  可以用@RestController代替
     * ResponseBody 注解，可以将如下类型的数据做成json：
     * 1）基本数据类型，如 boolean , String , int 等
     * 2) Map 类型数据
     * 3）集合或数组
     * 4）实体对象
     * 5）实体对象集合
     */
    @RequestMapping("/test1.do")
    @ResponseBody
    public Map<String, Object> test2(){
        Map<String , Object> map = new HashMap<String ,Object>();
        map.put("id", "1234567");
        map.put("name", "殷成健");
        return map;
    }
}
