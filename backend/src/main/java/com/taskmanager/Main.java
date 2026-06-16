package com.taskmanager; // Placing Main in the root of com.taskmanager

import com.taskmanager.model.TaskManager;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        manager = manager.addTask("Write Java code");
        manager = manager.addTask("Review pull request");
        manager = manager.addTask("Update documentation");

        System.out.println("Initial Tasks:");
        manager.printTasks();

        manager = manager.completeTask(2);

        System.out.println("\nAfter Completing Task 2:");
        manager.printTasks();

        manager = manager.removeTask(1);

        System.out.println("\nAfter Removing Task 1:");
        manager.printTasks();

        System.out.println("\nCompleted Tasks: " + manager.counttaskDone());
        System.out.println("Total Tasks: " + manager.countAll());
    }
}