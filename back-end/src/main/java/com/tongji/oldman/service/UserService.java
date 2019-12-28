package com.tongji.oldman.service;

import com.tongji.oldman.entity.User;
import com.tongji.oldman.entity.UserExample;
import com.tongji.oldman.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<User> getUsers(UserExample userExample) {
        return userMapper.selectByExample(userExample);
    }

    public long countUsers(UserExample userExample) {
        return userMapper.countByExample(userExample);
    }

    public int newUser(User user) {
        return userMapper.insertSelective(user);
    }

    public int deleteUser(UserExample userExample) {
        return userMapper.deleteByExample(userExample);
    }

    public int updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
