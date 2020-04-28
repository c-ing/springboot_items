package com.spring.demo.mapper.db2;

import com.spring.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther: cdc
 * @Date: 2020/4/27 16:16
 * @Description:
 */

//@Mapper
public interface UserMapper2 {

    List<User> selectUserList();

    void saveUser(User user);

    void updateUser(User user);
}
