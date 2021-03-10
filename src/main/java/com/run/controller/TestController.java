package com.run.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @PostMapping("/user/test")
    public String test(@RequestParam("name") String name){
        System.out.println(name);
        return "yes"+name+"收到了";
    }
}
