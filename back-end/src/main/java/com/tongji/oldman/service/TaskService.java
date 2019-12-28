package com.tongji.oldman.service;

import com.tongji.oldman.entity.Task;
import com.tongji.oldman.entity.TaskExample;
import com.tongji.oldman.mapper.TaskMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskMapper taskMapper;

    public TaskService(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    public List<Task> getTasks(TaskExample taskExample) {
        return taskMapper.selectByExample(taskExample);
    }

    public int newTask(Task task) {
        return taskMapper.insertSelective(task);
    }

    public long countTasks(TaskExample taskExample) {
        return taskMapper.countByExample(taskExample);
    }

    public int deleteTask(TaskExample taskExample) {
        return taskMapper.deleteByExample(taskExample);
    }

    public int updateTask(Task task) {
        return taskMapper.updateByPrimaryKeySelective(task);
    }
}
