package com.spring.demo.mapper.db1;

import com.spring.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * @Auther: cdc
 * @Date: 2020/4/27 16:16
 * @Description:
 */
//@Mapper
public interface UserMapper {

    List<User> selectUserList();

    void saveUser(User user);

    void updateUser(User user);
}
