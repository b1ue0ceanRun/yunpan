package com.run.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class File {
    private Integer id;      //id
    private String FileName;   //文件名
    private String newFileName;   //新的文件名
    private String path;        //路径
    private String size;            //大小
    private String type;          //类型
    private String uploadTime;     //上传时间
    private String username; //用户外键
    private String extension;  //拓展名
}
