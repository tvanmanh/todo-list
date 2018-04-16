package com.example.tranvanmanh.sqlitedatabase;

/**
 * Created by tranvanmanh on 4/14/2018.
 */

public class Task {

    private int id;
    private String nameTask;

    public Task(int id, String nameTask) {
        this.id = id;
        this.nameTask = nameTask;
    }

    public int getId() {
        return id;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }
}
