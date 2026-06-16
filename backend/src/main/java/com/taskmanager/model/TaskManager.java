package com.taskmanager.model;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class TaskManager {
    private final List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    private TaskManager(List<Task> tasks) {
        this.tasks = tasks;
    }

    // Add Task (returns new manager)
    public TaskManager addTask(String taskInfo) {
        int taskNo = tasks.size() + 1;
        List<Task> newTasks = new ArrayList<>(tasks);
        newTasks.add(new Task(taskNo, taskInfo, false));
        return new TaskManager(newTasks);
    }

    // Complete Task
    public TaskManager completeTask(int taskNo) {
        List<Task> newTasks = tasks.stream()
                .map(task -> task.gettaskNo() == taskNo ? task.marktaskDone() : task)
                .collect(Collectors.toList());
        return new TaskManager(newTasks);
    }

    // Remove Task
    public TaskManager removeTask(int taskNo) {
        List<Task> newTasks = tasks.stream()
                .filter(task -> task.gettaskNo() != taskNo)
                .collect(Collectors.toList());
        return new TaskManager(newTasks);
    }

    // Display All Tasks
    public void printTasks() {
        tasks.forEach(System.out::println);
    }

    // Get Completed Task Count using reduce
    public long counttaskDone() {
        return tasks.stream()
                .filter(Task::istaskDone)
                .count();
    }

    // Get Total Tasks
    public long countAll() {
        return tasks.size();
    }

    // Add this method to retrieve all tasks for the controller
    public List<Task> getAllTasks() {
        return new ArrayList<>(this.tasks);
    }
}