package com.taskmanager.controller;

import com.taskmanager.model.Task;
import com.taskmanager.model.TaskManager; // Changed import to TaskManager
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:3000") // React dev server
public class TaskController {

    private TaskManager taskManager = new TaskManager(); // Using TaskManager directly

    @GetMapping
    public List<Task> getAllTasks() {
        return taskManager.getAllTasks();
    }

    @PostMapping
    public List<Task> addTask(@RequestBody Task task) {
        taskManager = taskManager.addTask(task.gettaskInfo()); // Assuming Task object in request has taskInfo
        return taskManager.getAllTasks();
    }

    @PutMapping("/{taskNo}/complete")
    public List<Task> completeTask(@PathVariable int taskNo) {
        taskManager = taskManager.completeTask(taskNo);
        return taskManager.getAllTasks();
    }

    @DeleteMapping("/{taskNo}")
    public List<Task> deleteTask(@PathVariable int taskNo) {
        taskManager = taskManager.removeTask(taskNo);
        return taskManager.getAllTasks();
    }
}