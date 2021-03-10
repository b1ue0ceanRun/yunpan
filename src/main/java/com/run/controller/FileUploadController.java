package com.run.controller;

import com.run.mapper.FileMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Slf4j
@RestController
public class FileUploadController {

    @Autowired
    private FileMapper fileMapper;

    @PostMapping("/user/upload")
    public Map<String, Object> upload(@RequestPart("files") MultipartFile[] files) throws IOException {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        int space = fileMapper.findUserSpace(authentication.getName());
        float gb = (float)space / 1024 /1024 /1024 ;
        if (space>1048576000){
            Map<String,Object> map = new HashMap<>();
            map.put("status",200);
            map.put("msg","空间已满");
            return map;
        }



        if (files.length>0){
            for(MultipartFile file : files){
                if(!file.isEmpty()){

                    //路径
                    String path = "D:\\IMGUPLOAD";


                    //获取文件名
                    String originalFilename = file.getOriginalFilename();

                    //获取文件大小
                    Long size = file.getSize();

                    //获取文件类型
                    String type = file.getContentType();

                    //获取扩展文件名
                    String extension = "." + FilenameUtils.getExtension(file.getOriginalFilename());

                    //获取当前上传的时间
                    Date date = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String uploadTime = formatter.format(date);

                    //命名新文件名

                    String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + UUID.randomUUID().toString().replace("-", "") + extension;;
                    

                    //处理文件上传
                    file.transferTo(new File(path+"\\"+newFileName));

                    //将信息存贮至数据库
                    com.run.pojo.File uploadFile = new com.run.pojo.File();
                    uploadFile.setFileName(originalFilename);
                    uploadFile.setSize(String.valueOf(size));
                    uploadFile.setType(type);
                    uploadFile.setPath(path+"\\"+newFileName);
                    uploadFile.setUploadTime(uploadTime);
                    uploadFile.setUsername(authentication.getName());
                    uploadFile.setExtension(extension);
                    uploadFile.setNewFileName(newFileName);

                    System.out.println(uploadFile);

                    fileMapper.saveFile(uploadFile);
                    fileMapper.saveFileToCheck(uploadFile);

                }
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("status",200);
        map.put("msg","上传成功");
        map.put("已使用空间(GB)",gb);
        return map;
    }



}









