package com.tongji.oldman.controller;

import com.alibaba.fastjson.JSON;
import com.tongji.oldman.entity.User;
import com.tongji.oldman.entity.UserExample;
import com.tongji.oldman.response.UserResponse;
import com.tongji.oldman.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(Integer uid, String password) {
        int success = 0;
        int wrongpassword = 0;
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUidEqualTo(uid);
        List<User> users = userService.getUsers(userExample);
        int size = users.size();
        User user;
        if (size == 1) {
            user = users.get(0);
            if (users.get(0).getPassword().equals(password)) {
                success = 1;
            }
            else {
                wrongpassword = 1;
            }
        }
        else {
            user = new User();
        }
        return JSON.toJSONString(new UserResponse(success, user, wrongpassword));
    }

}
