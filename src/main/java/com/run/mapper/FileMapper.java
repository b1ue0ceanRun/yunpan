package com.run.mapper;

import com.run.pojo.File;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FileMapper {

    //根据登录用户名获取用户的文件列表
    List<File> findByUserName(String username);

    //根据文件id获取文件信息
    File findByFileId(Integer id);


    //保存文件信息
    void saveFile(File file);

    //根据文件id删除文件记录
    void deleteFile(Integer id);

    // ==================================================================


    //把文件保存到数据库 审核过后再删掉
    void saveFileToCheck(File file);

    //对于审核过后的文件进行删除！
    void deleteCheckedFile(Integer id);

    //展示所有未审核的图片
    List<File> showUncheckedFile();

    //根据id找到图片信息
    File findUncheckedFileById(Integer id);

    //查找一个人已使用的储存空间
    int findUserSpace(String username);







}
