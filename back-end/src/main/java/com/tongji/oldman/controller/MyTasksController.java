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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/mytasks")
public class MyTasksController {
    private final TaskService taskService;
    private final OldService oldService;

    public MyTasksController(TaskService taskService, OldService oldService) {
        this.taskService = taskService;
        this.oldService = oldService;
    }

    private static class Req {
        private Integer uid;
        private Integer tid;
        private Integer oid;
        private String title;
        private  String image;
        private String description;
        private Integer frequency;
        private Integer allocated;
        private Integer finished;

        public void setUid(Integer uid) {
            this.uid = uid;
        }

        public void setTid(Integer tid) {
            this.tid = tid;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setFrequency(Integer frequency) {
            this.frequency = frequency;
        }

        public void setFinished(Integer finished) {
            this.finished = finished;
        }

        public void setAllocated(Integer allocated) {
            this.allocated = allocated;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setOid(Integer oid) {
            this.oid = oid;
        }
    }

    @PostMapping("/gettasks")
    public String getTasks(@RequestBody Req req) {
        TaskExample taskExample = new TaskExample();
        TaskExample.Criteria criteria = taskExample.createCriteria();
        criteria.andUidEqualTo(req.uid);
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
    public String tickTask(@RequestBody Req req) {
        TaskExample taskExample = new TaskExample();
        TaskExample.Criteria criteria = taskExample.createCriteria();
        criteria.andTidEqualTo(req.tid);
        int success = 0;
        List<Task> tasks = taskService.getTasks(taskExample);
        Task task = new Task();
        task.setTid(req.tid);
        task.setFinished(1 - tasks.get(0).getFinished());
        success = 1;
        UserResponse userResponse = new UserResponse(success);
        return JSON.toJSONString(userResponse);
    }

    @PostMapping("/transfertask")
    public String transferTask(@RequestBody Req req) {
        Task task = new Task();
        task.setTid(req.tid);
        task.setAllocated(0);
        int transfer = taskService.updateTask(task);
        UserResponse userResponse = new UserResponse(1);
        return JSON.toJSONString(userResponse);
    }

    @PostMapping("/createtask")
    public String createTask(@RequestBody Req req) {
        OldExample oldExample = new OldExample();
        OldExample.Criteria criteria = oldExample.createCriteria();
        criteria.andOidEqualTo(req.oid);
        List<Old> oldList = oldService.getOlds(oldExample);
        int notexist = 1;
        int noauth = 1;
        int success = 0;
        int size = oldList.size();
        if (size == 1 || req.oid == -1) {
            notexist = 0;
            if (req.oid == -1 || oldList.get(0).getResponsibility() == req.uid) {
                noauth = 0;
                Task task = new Task();
                task.setUid(req.uid);
                task.setTitle(req.title);
                task.setImage(req.image);
                task.setDescription(req.description);
                task.setFrequency(req.frequency);
                task.setAllocated(req.allocated);
                task.setFinished(req.finished);
                if (req.oid != -1)
                    task.setOid(req.oid);
                taskService.newTask(task);
                success = 1;
            }
        }
        UserResponse userResponse = new UserResponse(success, notexist, noauth);
        return JSON.toJSONString(userResponse);
    }
}
