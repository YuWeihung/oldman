package com.tongji.oldman.controller;

import com.alibaba.fastjson.JSON;
import com.tongji.oldman.entity.User;
import com.tongji.oldman.entity.UserExample;
import com.tongji.oldman.response.UserResponse;
import com.tongji.oldman.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/myinfo")
public class MyInfoController {
    private final UserService userService;

    public MyInfoController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/changepassword")
    public String changePassword(Integer uid, String password, String newpassword) {
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
                User user1 = new User();
                user1.setUid(uid);
                user1.setPassword(newpassword);
                int updateUser = userService.updateUser(user1);
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
