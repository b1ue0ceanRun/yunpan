package com.run.controller;


import com.run.mapper.UserMapper;
import com.run.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RegisterController {
    @Autowired
    private UserMapper userMapper;


    @GetMapping("/Register")
    public Map Register(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("phone") String phone,
                        @RequestParam("email") String email) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String encodePassword = encoder.encode(password);

        User user = new User();
        user.setUsername(username);
        user.setPassword(encodePassword);
        user.setPhone(phone);
        user.setEmail(email);

        System.out.println(user);

        Map<String,Object> map1 = new HashMap<>();
        Map<String,Object> map2 = new HashMap<>();
        map1.put("status",200);
        map1.put("msg","注册成功");
        map2.put("status",401);
        map2.put("msg","用户名重复");


        if(userMapper.queryUserByUsername(username) == null){
            userMapper.addUser(user);
        }else {
            return map2;
        }


       return map1;


    }
}
