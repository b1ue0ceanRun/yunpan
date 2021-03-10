package com.run.controller;

import com.run.mapper.FileMapper;
import com.run.pojo.File;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Slf4j
@RestController
public class FileDownloadController {

    @Autowired
    private FileMapper fileMapper;

    @GetMapping("/user/download")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam("id") String id) throws IOException {
        File file = fileMapper.findByFileId(Integer.valueOf(id));
        String filePath = file.getPath();
        FileSystemResource downloadFile = new FileSystemResource(filePath);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", downloadFile.getFilename()));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(downloadFile.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(downloadFile.getInputStream()));

    }

}



