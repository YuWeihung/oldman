package com.tongji.oldman.controller;

import com.alibaba.fastjson.JSON;
import com.tongji.oldman.entity.User;
import com.tongji.oldman.entity.UserExample;
import com.tongji.oldman.response.UserResponse;
import com.tongji.oldman.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    private static class Req {
        private Integer uid;
        private String password;

        public void setUid(Integer uid) {
            this.uid = uid;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody Req req) {
        int success = 0;
        int wrongpassword = 0;
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUidEqualTo(req.uid);
        List<User> users = userService.getUsers(userExample);
        int size = users.size();
        User user;
        if (size == 1) {
            user = users.get(0);
            if (users.get(0).getPassword().equals(req.password)) {
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
