package com.tongji.oldman.scheduling;

import com.tongji.oldman.entity.Task;
import com.tongji.oldman.entity.TaskExample;
import com.tongji.oldman.service.TaskService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskScheduling {

    private final TaskService  taskService;

    public TaskScheduling(TaskService taskService) {
        this.taskService = taskService;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void freshTaskDaily() {
        TaskExample taskExample = new TaskExample();
        TaskExample.Criteria criteria = taskExample.createCriteria();
        criteria.andFrequencyEqualTo(1);
        List<Task> tasks = taskService.getTasks(taskExample);
        int size = tasks.size();
        for (int i = 0; i < size; i++) {
            Task task = new Task();
            task.setTid(tasks.get(i).getTid());
            task.setFinished(0);
            int update = taskService.updateTask(task);
        }
    }

    @Scheduled(cron = "0 0 0 ? * MON")
    public void freshTaskWeekly() {
        TaskExample taskExample = new TaskExample();
        TaskExample.Criteria criteria = taskExample.createCriteria();
        criteria.andFrequencyEqualTo(7);
        List<Task> tasks = taskService.getTasks(taskExample);
        int size = tasks.size();
        for (int i = 0; i < size; i++) {
            Task task = new Task();
            task.setTid(tasks.get(i).getTid());
            task.setFinished(0);
            int update = taskService.updateTask(task);
        }
    }

    @Scheduled(cron = "0 0 0 1 * ?")
    public void freshTaskMonthly() {
        TaskExample taskExample = new TaskExample();
        TaskExample.Criteria criteria = taskExample.createCriteria();
        criteria.andFrequencyEqualTo(30);
        List<Task> tasks = taskService.getTasks(taskExample);
        int size = tasks.size();
        for (int i = 0; i < size; i++) {
            Task task = new Task();
            task.setTid(tasks.get(i).getTid());
            task.setFinished(0);
            int update = taskService.updateTask(task);
        }
    }
}
