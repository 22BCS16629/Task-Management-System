package com.taskmanager.model;

public class Task {
    private final int taskNo;
    private final String taskInfo;
    private final boolean taskDone;

    public Task(int taskNo, String taskInfo, boolean taskDone) {
        this.taskNo = taskNo;
        this.taskInfo = taskInfo;
        this.taskDone = taskDone;
    }

    public int gettaskNo() { return taskNo; }
    public String gettaskInfo() { return taskInfo; }
    public boolean istaskDone() { return taskDone; }

    public Task marktaskDone() {
        return new Task(taskNo, taskInfo, true);
    }

    @Override
    public String toString() {
        return String.format("Task{id=%d, desc='%s', taskDone=%b}", taskNo, taskInfo, taskDone);
    }
}