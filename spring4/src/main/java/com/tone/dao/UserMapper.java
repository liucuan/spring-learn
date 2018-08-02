package com.tone.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author zhaoxiang.liu
 * @date 2018/8/2
 */
public interface UserMapper {

    @Select(value = "select id, name, age, password from user where name = #{name}")
    User loadUserByUsername(@Param("name") String name);

    @Insert(value = "insert into user (name, age, password) value(#{name},#{age},#{password})")
    void saveUser(User user);
}
