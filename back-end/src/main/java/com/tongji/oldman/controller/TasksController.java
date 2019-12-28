package com.tongji.oldman.controller;

import com.alibaba.fastjson.JSON;
import com.tongji.oldman.entity.Old;
import com.tongji.oldman.entity.OldExample;
import com.tongji.oldman.entity.Task;
import com.tongji.oldman.entity.TaskExample;
import com.tongji.oldman.response.ATaskListResponse;
import com.tongji.oldman.response.UserResponse;
import com.tongji.oldman.service.OldService;
import com.tongji.oldman.service.TaskService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    private final TaskService taskService;
    private final OldService oldService;

    public TasksController(TaskService taskService, OldService oldService) {
        this.taskService = taskService;
        this.oldService = oldService;
    }

    @PostMapping("/agettasks")
    public String aGetTasks(Integer uid) {
        TaskExample taskExample = new TaskExample();
        TaskExample.Criteria criteria = taskExample.createCriteria();
        List<Task> tasks;
        if (uid != -1) {
            criteria.andUidEqualTo(uid);
        }
        else {
            criteria.andAllocatedEqualTo(0);
        }
        tasks = taskService.getTasks(taskExample);
        ATaskListResponse taskListResponse = new ATaskListResponse(tasks);
        return JSON.toJSONString(taskListResponse);
    }

    @PostMapping("acreatetask")
    public String aCreateTask(Integer uid, Integer oid, String title, String image, String description,
                              Integer frequency, Integer allocated, Integer finished) {
        OldExample oldExample = new OldExample();
        OldExample.Criteria criteria = oldExample.createCriteria();
        criteria.andOidEqualTo(oid);
        List<Old> oldList = oldService.getOlds(oldExample);
        int notexist = 1;
        int success = 0;
        int size = oldList.size();
        if (size == 1 || oid == -1) {
            notexist = 0;
            Task task = new Task();
            task.setUid(uid);
            task.setTitle(title);
            task.setImage(image);
            task.setDescription(description);
            task.setFrequency(frequency);
            task.setAllocated(allocated);
            task.setFinished(finished);
            if (oid != -1)
                task.setOid(oid);
            taskService.newTask(task);
            success = 1;
        }
        UserResponse userResponse = new UserResponse(success, notexist, -1);
        return JSON.toJSONString(userResponse);
    }

    @PostMapping("deletetask")
    public String deleteTask(Integer tid) {
        TaskExample taskExample = new TaskExample();
        TaskExample.Criteria criteria = taskExample.createCriteria();
        criteria.andTidEqualTo(tid);
        int delete = taskService.deleteTask(taskExample);
        int success = 0;
        if (delete == 1)
            success = 1;
        UserResponse userResponse = new UserResponse(success);
        return JSON.toJSONString(userResponse);
    }
}
