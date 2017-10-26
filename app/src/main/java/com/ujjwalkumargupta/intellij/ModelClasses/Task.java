package com.ujjwalkumargupta.intellij.ModelClasses;

/**
 * Created by Ujjwal Kumar Gupta on 23-Jul-17.
 */

public class Task {

    public boolean isChecked;
    public int taskId;
    public String  taskName, taskDescription, taskCreated, taskUpdated;

    public Task(){
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskCreated() {
        return taskCreated;
    }

    public void setTaskCreated(String taskCreated) {
        this.taskCreated = taskCreated;
    }

    public String getTaskUpdated() {
        return taskUpdated;
    }

    public void setTaskUpdated(String taskUpdated) {
        this.taskUpdated = taskUpdated;
    }
}
