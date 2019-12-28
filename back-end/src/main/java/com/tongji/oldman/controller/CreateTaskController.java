package com.tongji.oldman.controller;

import com.alibaba.fastjson.JSON;
import com.tongji.oldman.entity.Old;
import com.tongji.oldman.entity.OldExample;
import com.tongji.oldman.entity.Task;
import com.tongji.oldman.response.UserResponse;
import com.tongji.oldman.service.OldService;
import com.tongji.oldman.service.TaskService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CreateTaskController {

    private final TaskService taskService;
    private final OldService oldService;

    public CreateTaskController(TaskService taskService, OldService oldService) {
        this.taskService = taskService;
        this.oldService = oldService;
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
