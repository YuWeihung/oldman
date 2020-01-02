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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/tasks")
public class TasksController {
    private final TaskService taskService;
    private final OldService oldService;

    public TasksController(TaskService taskService, OldService oldService) {
        this.taskService = taskService;
        this.oldService = oldService;
    }

    private static class Req {
        private Integer tid;
        private Integer uid;
        private Integer oid;
        private String title;
        private String image;
        private String description;
        private Integer frequency;
        private Integer allocated;
        private Integer finished;

        public void setTid(Integer tid) {
            this.tid = tid;
        }

        public void setUid(Integer uid) {
            this.uid = uid;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setOid(Integer oid) {
            this.oid = oid;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setAllocated(Integer allocated) {
            this.allocated = allocated;
        }

        public void setFinished(Integer finished) {
            this.finished = finished;
        }

        public void setFrequency(Integer frequency) {
            this.frequency = frequency;
        }
    }

    @PostMapping("/agettasks")
    public String aGetTasks(@RequestBody Req req) {
        TaskExample taskExample = new TaskExample();
        TaskExample.Criteria criteria = taskExample.createCriteria();
        List<Task> tasks;
        if (req.uid != -1) {
            criteria.andUidEqualTo(req.uid);
        }
        else {
            criteria.andAllocatedEqualTo(0);
        }
        tasks = taskService.getTasks(taskExample);
        ATaskListResponse taskListResponse = new ATaskListResponse(tasks);
        return JSON.toJSONString(taskListResponse);
    }

    @PostMapping("/acreatetask")
    public String aCreateTask(@RequestBody Req req) {
        OldExample oldExample = new OldExample();
        OldExample.Criteria criteria = oldExample.createCriteria();
        criteria.andOidEqualTo(req.oid);
        List<Old> oldList = oldService.getOlds(oldExample);
        int notexist = 1;
        int success = 0;
        int size = oldList.size();
        if (size == 1 || req.oid == -1) {
            notexist = 0;
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
        UserResponse userResponse = new UserResponse(success, notexist, -1);
        return JSON.toJSONString(userResponse);
    }

    @PostMapping("/deletetask")
    public String deleteTask(@RequestBody Req req) {
        TaskExample taskExample = new TaskExample();
        TaskExample.Criteria criteria = taskExample.createCriteria();
        criteria.andTidEqualTo(req.tid);
        int delete = taskService.deleteTask(taskExample);
        int success = 0;
        if (delete == 1)
            success = 1;
        UserResponse userResponse = new UserResponse(success);
        return JSON.toJSONString(userResponse);
    }

    @PostMapping("/allocatetask")
    public String allocateTask(@RequestBody Req req) {
        Task task = new Task();
        task.setTid(req.tid);
        task.setUid(req.uid);
        int success = 0;
        int update = taskService.updateTask(task);
        if (update == 1)
            success = 1;
        UserResponse userResponse = new UserResponse(success);
        return JSON.toJSONString(userResponse);
    }
}
