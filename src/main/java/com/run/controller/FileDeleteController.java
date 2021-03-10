package com.run.controller;

import com.run.mapper.FileMapper;
import com.run.pojo.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FileDeleteController {

    @Autowired
    private FileMapper fileMapper;

    @GetMapping("/delete")
    public Map delete(@RequestParam("id") String id){



        //还要删除文件 （上网部署的时候需要该地址）
        File file = fileMapper.findByFileId(Integer.valueOf(id));
        java.io.File deleteFile = new java.io.File(file.getPath());
        if (deleteFile.exists()) {
            deleteFile.delete();
        }


        //数据库中删除
        fileMapper.deleteFile(Integer.valueOf(id));
        Map<String,Object> map = new HashMap<>();
        map.put("status",200);
        map.put("msg","删除成功");
        return map;



    }
}
