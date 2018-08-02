package com.tone.controller;

import com.tone.dao.User;
import com.tone.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaoxiang.liu
 * @date 2018/8/2
 */
@RestController
public class UserController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/user/{name}")
    public User findUser(@PathVariable("name") String name) {
        return userMapper.loadUserByUsername(name);
    }
}
