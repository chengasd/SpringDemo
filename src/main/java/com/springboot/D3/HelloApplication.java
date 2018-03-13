package com.springboot.D3;

        import com.springboot.D3.exception.MyException;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.ModelMap;
        import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloApplication {

    @RequestMapping("/hello1")
    public String hello(ModelMap map){
        map.put("host","miachelyin");
        return "index";
    }

    @RequestMapping("/wrong")
    public String hello() throws Exception {
        throw new Exception("发生错误");
    }

    @RequestMapping("/json")
    public String json() throws MyException {
        throw new MyException("发生错误2");
    }
}
