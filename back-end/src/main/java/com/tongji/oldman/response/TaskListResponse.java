package com.tongji.oldman.response;

import com.tongji.oldman.entity.Task;

import java.util.List;

public class TaskListResponse {
    List<TaskResponse> tasks;
    public TaskListResponse(List<TaskResponse> tasks) {
        this.tasks = tasks;
    }

    public List<TaskResponse> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskResponse> tasks) {
        this.tasks = tasks;
    }
}
