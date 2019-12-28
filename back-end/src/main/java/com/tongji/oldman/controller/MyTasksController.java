package com.tongji.oldman.controller;

import com.alibaba.fastjson.JSON;
import com.tongji.oldman.entity.Old;
import com.tongji.oldman.entity.OldExample;
import com.tongji.oldman.entity.Task;
import com.tongji.oldman.entity.TaskExample;
import com.tongji.oldman.response.TaskListResponse;
import com.tongji.oldman.response.TaskResponse;
import com.tongji.oldman.response.UserResponse;
import com.tongji.oldman.service.OldService;
import com.tongji.oldman.service.TaskService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mytasks")
public class MyTasksController {
    private final TaskService taskService;
    private final OldService oldService;

    public MyTasksController(TaskService taskService, OldService oldService) {
        this.taskService = taskService;
        this.oldService = oldService;
    }

    @PostMapping("/gettasks")
    public String getTasks(Integer uid) {
        TaskExample taskExample = new TaskExample();
        TaskExample.Criteria criteria = taskExample.createCriteria();
        criteria.andUidEqualTo(uid);
        List<Task> taskList = taskService.getTasks(taskExample);
        int size = taskList.size();
        List<TaskResponse> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Integer oid = taskList.get(i).getOid();
            OldExample oldExample = new OldExample();
            OldExample.Criteria criteria1 = oldExample.createCriteria();
            criteria1.andOidEqualTo(oid);
            List<Old> olds = oldService.getOlds(oldExample);
            TaskResponse taskResponse = new TaskResponse(taskList.get(i), olds.get(0));
            list.add(taskResponse);
        }
        TaskListResponse taskListResponse = new TaskListResponse(list);
        return JSON.toJSONString(taskListResponse);
    }

    @PostMapping("/ticktask")
    public String tickTask(Integer tid) {
        TaskExample taskExample = new TaskExample();
        TaskExample.Criteria criteria = taskExample.createCriteria();
        criteria.andTidEqualTo(tid);
        int success = 0;
        List<Task> tasks = taskService.getTasks(taskExample);
        Task task = new Task();
        task.setTid(tid);
        task.setFinished(1 - tasks.get(0).getFinished());
        success = 1;
        UserResponse userResponse = new UserResponse(success);
        return JSON.toJSONString(userResponse);
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

    @PostMapping("/createtask")
    public String createTask(Integer uid, Integer oid, String title, String image,
                             String description, Integer frequency, Integer allocated, Integer finished) {
        OldExample oldExample = new OldExample();
        OldExample.Criteria criteria = oldExample.createCriteria();
        criteria.andOidEqualTo(oid);
        List<Old> oldList = oldService.getOlds(oldExample);
        int notexist = 1;
        int noauth = 1;
        int success = 0;
        int size = oldList.size();
        if (size == 1 || oid == -1) {
            notexist = 0;
            if (oid == -1 || oldList.get(0).getResponsibility() == uid) {
                noauth = 0;
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
        }
        UserResponse userResponse = new UserResponse(success, notexist, noauth);
        return JSON.toJSONString(userResponse);
    }
}
