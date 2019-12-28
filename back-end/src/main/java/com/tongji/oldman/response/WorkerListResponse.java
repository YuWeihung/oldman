package com.tongji.oldman.response;

import com.tongji.oldman.entity.User;

import java.util.List;

public class WorkerListResponse {
    private List<User> workers;

    public WorkerListResponse(List<User> workers) {
        this.workers = workers;
    }

    public List<User> getWorkers() {
        return workers;
    }

    public void setWorkers(List<User> workers) {
        this.workers = workers;
    }
}
