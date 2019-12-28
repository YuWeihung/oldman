package com.tongji.oldman.response;

import com.tongji.oldman.entity.Task;

import java.util.List;

public class ATaskListResponse {
    private List<Task> tasks;

    public ATaskListResponse(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
