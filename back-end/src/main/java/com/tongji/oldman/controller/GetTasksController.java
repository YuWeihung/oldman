package com.tongji.oldman.controller;

import com.alibaba.fastjson.JSON;
import com.tongji.oldman.entity.Old;
import com.tongji.oldman.entity.OldExample;
import com.tongji.oldman.entity.Task;
import com.tongji.oldman.entity.TaskExample;
import com.tongji.oldman.response.TaskListResponse;
import com.tongji.oldman.response.TaskResponse;
import com.tongji.oldman.service.OldService;
import com.tongji.oldman.service.TaskService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GetTasksController {
    private final TaskService taskService;
    private final OldService oldService;

    public GetTasksController(TaskService taskService, OldService oldService) {
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
}
