package com.run.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.run.mapper.FileMapper;
import com.run.pojo.File;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xmlunit.util.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FileShowController {

    @Autowired
    private FileMapper fileMapper;

    @GetMapping("/user/show")
    public PageInfo<File> show(@RequestParam("pageNum") String pageNum,
                               @RequestParam("pageSize") String pageSize
                               ){

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));

        List<File> fileList = fileMapper.findByUserName(authentication.getName());
        return new PageInfo<>(fileList);

    }

    @GetMapping("/user/showNum")
    public Map<String, Object> show(){

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        List<File> fileList = fileMapper.findByUserName(authentication.getName());
        int size = fileList.size();
        Map<String,Object> map = new HashMap<>();
        map.put("num",size);

        return map;

    }
}

