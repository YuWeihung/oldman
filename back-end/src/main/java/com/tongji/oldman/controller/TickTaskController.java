package com.tongji.oldman.controller;

import com.tongji.oldman.entity.TaskExample;
import com.tongji.oldman.service.TaskService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TickTaskController {
    private final TaskService taskService;

    public TickTaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/ticktask")
    public String tickTask(Integer tid) {
        return "";
    }
}
