package com.tongji.oldman.controller;

import com.alibaba.fastjson.JSON;
import com.tongji.oldman.entity.User;
import com.tongji.oldman.entity.UserExample;
import com.tongji.oldman.response.UserResponse;
import com.tongji.oldman.response.WorkerListResponse;
import com.tongji.oldman.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/workers")
public class WorkersController {
    public final UserService userService;

    public WorkersController(UserService userService) {
        this.userService = userService;
    }

    private static class Req {
        private String name;
        private String avatar;
        private String password;
        private Integer uid;
        private String newpassword;

        public void setName(String name) {
            this.name = name;
        }

        public void setUid(Integer uid) {
            this.uid = uid;
        }

        public void setNewpassword(String newpassword) {
            this.newpassword = newpassword;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
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
    public String createWorker(@RequestBody Req req) {
        User user = new User();
        user.setName(req.name);
        user.setAvatar(req.avatar);
        user.setAdmin(0);
        user.setPassword(req.password);
        int insert = userService.newUser(user);
        int success = 0;
        if (insert == 1)
            success = 1;
        UserResponse userResponse = new UserResponse(success);
        return JSON.toJSONString(userResponse);
    }

    @PostMapping("/achangepassword")
    public String aChangePassword(@RequestBody Req req) {
        User user = new User();
        user.setUid(req.uid);
        user.setPassword(req.newpassword);
        int update = userService.updateUser(user);
        int success = 0;
        if (update == 1)
            success = 1;
        UserResponse userResponse = new UserResponse(success);
        return JSON.toJSONString(userResponse);
    }

    @PostMapping("/deleteworker")
    public String deleteWorker(@RequestBody Req req) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUidEqualTo(req.uid);
        int delete = userService.deleteUser(userExample);
        int success = 0;
        if (delete == 1)
            success = 1;
        UserResponse userResponse = new UserResponse(success);
        return JSON.toJSONString(userResponse);
    }
}
