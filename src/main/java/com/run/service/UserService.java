package com.run.service;

import com.run.mapper.UserMapper;
import com.run.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.queryUserByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("账户不存在！");
        }
        user.setRole(user.getRole());
        return user;
    }
}
