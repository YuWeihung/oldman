package com.tongji.oldman.response;

import com.tongji.oldman.entity.Old;
import com.tongji.oldman.entity.Task;
import com.tongji.oldman.entity.User;

public class TaskResponse {
    private Integer tid;
    private String title;
    private String image;
    private String oname;
    private String uname;
    private String description;
    private Integer frequency;
    private Integer allocated;
    private Integer finished;

    public TaskResponse(Task task, Old old, User user) {
        this.tid = task.getTid();
        this.title = task.getTitle();
        this.image = task.getImage();
        this.oname = old.getName();
        this.uname = user.getName();
        this.description = task.getDescription();
        this.frequency = task.getFrequency();
        this.allocated = task.getAllocated();
        this.finished = task.getFinished();
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

    public void setImage(String image) {
        this.image = image;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTitle() {
        return title;
    }

    public Integer getAllocated() {
        return allocated;
    }

    public Integer getFinished() {
        return finished;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public Integer getTid() {
        return tid;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getOname() {
        return oname;
    }
}
