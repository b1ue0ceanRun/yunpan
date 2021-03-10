package com.run.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.run.mapper.FileMapper;
import com.run.pojo.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FileCheckController {

    @Autowired
    private FileMapper fileMapper;

    @GetMapping("/admin/CheckFiles")
    public PageInfo<File> ShowUncheckedFile(@RequestParam("pageNum") String pageNum,
                                            @RequestParam("pageSize") String pageSize){
        PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        List<File> fileList = fileMapper.showUncheckedFile();
        return new PageInfo<>(fileList);
    }

    @GetMapping("/admin/deleteNotPassFile")
    public Map deleteNotPassFile(@RequestParam("id") String id){
        //删除本地文件
        File file = fileMapper.findUncheckedFileById(Integer.valueOf(id));
        java.io.File NotPassFile = new java.io.File(file.getPath());
        NotPassFile.delete();
        Map<String,Object> map = new HashMap<>();
        map.put("status",200);
        map.put("msg","删除成功");




        //删除数据库记录
        fileMapper.deleteCheckedFile(Integer.valueOf(id));
        fileMapper.deleteFile(Integer.valueOf(id));

        return map;

    }




    //审核成功删除数据
    @GetMapping("/admin/passFile")
    public Map passFile(@RequestParam("id") String id){
        //删除数据库记录

        fileMapper.deleteCheckedFile(Integer.valueOf(id));
        Map<String,Object> map = new HashMap<>();
        map.put("status",200);
        map.put("msg","审核成功");
        return map;

    }





}
