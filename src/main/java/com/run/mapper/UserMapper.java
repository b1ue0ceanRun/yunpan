package com.run.mapper;

import com.run.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface UserMapper {

    List<User> queryUserList();

    int addUser(User user);

    int updateUser(User user);

    int deleteUser(String username);

    User queryUserByUsername(String username);


}
