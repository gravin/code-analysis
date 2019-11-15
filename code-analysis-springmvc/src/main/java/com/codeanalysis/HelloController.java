package com.codeanalysis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {


    @GetMapping("/hello")
    public @ResponseBody Map<String, Object> sayHello(@RequestParam("name") String name) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("-------hello " + name + "--------");//会在控制台打印hello world
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "0");
        map.put("msg", "hello " + name);
        return map;//并跳转到welcome这个jsp页面
    }

}