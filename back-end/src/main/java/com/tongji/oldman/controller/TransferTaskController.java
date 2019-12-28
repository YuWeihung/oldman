package com.tongji.oldman.controller;

import com.alibaba.fastjson.JSON;
import com.tongji.oldman.entity.Task;
import com.tongji.oldman.response.UserResponse;
import com.tongji.oldman.service.TaskService;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferTaskController {
    private final TaskService taskService;

    public TransferTaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/transfertask")
    public String transferTask(Integer tid) {
        Task task = new Task();
        task.setTid(tid);
        task.setAllocated(0);
        int transfer = taskService.updateTask(task);
        UserResponse userResponse = new UserResponse(1);
        return JSON.toJSONString(userResponse);
    }
}
