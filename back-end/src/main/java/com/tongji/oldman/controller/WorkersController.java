package com.tongji.oldman.controller;

import com.alibaba.fastjson.JSON;
import com.tongji.oldman.entity.User;
import com.tongji.oldman.entity.UserExample;
import com.tongji.oldman.response.UserResponse;
import com.tongji.oldman.response.WorkerListResponse;
import com.tongji.oldman.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/workers")
public class WorkersController {
    public final UserService userService;

    public WorkersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/getworkers")
    public String getWorkers() {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andAdminEqualTo(0);
        List<User> users = userService.getUsers(userExample);
        WorkerListResponse workerListResponse = new WorkerListResponse(users);
        return JSON.toJSONString(workerListResponse);
    }

    @PostMapping("/createworker")
    public String createWorker(String name, String avatar, String password) {
        User user = new User();
        user.setName(name);
        user.setAvatar(avatar);
        user.setAdmin(0);
        user.setPassword(password);
        int insert = userService.newUser(user);
        int success = 0;
        if (insert == 1)
            success = 1;
        UserResponse userResponse = new UserResponse(success);
        return JSON.toJSONString(userResponse);
    }

    @PostMapping("/achangepassword")
    public String aChangePassword(Integer uid, String newpassword) {
        User user = new User();
        user.setUid(uid);
        user.setPassword(newpassword);
        int update = userService.updateUser(user);
        int success = 0;
        if (update == 1)
            success = 1;
        UserResponse userResponse = new UserResponse(success);
        return JSON.toJSONString(userResponse);
    }

    @PostMapping("/deleteworker")
    public String deleteWorker(Integer uid) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUidEqualTo(uid);
        int delete = userService.deleteUser(userExample);
        int success = 0;
        if (delete == 1)
            success = 1;
        UserResponse userResponse = new UserResponse(success);
        return JSON.toJSONString(userResponse);
    }
}
