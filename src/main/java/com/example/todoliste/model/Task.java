package com.example.todoliste.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Task {
    private StringProperty task;
    private StringProperty date;

    private final StringProperty status = new SimpleStringProperty();

    public Task(String task, String date){
        this.task = new SimpleStringProperty(task);
        this.date = new SimpleStringProperty(date);
    }

    public String getTask() {
        return task.get();
    }

    public void setTask(String task) {
        this.task.set(task);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public void setStatus(String value){
        status.set(value);
    }

    public String getStatus(){
        return status.get();
    }

    public StringProperty taskProperty(){
        if(task == null){
            task = new SimpleStringProperty("-");
        }
        return task;
    }

    public StringProperty dateProperty(){
        if(date == null){
            date = new SimpleStringProperty("-");
        }
        return date;
    }

    public StringProperty statusProperty(){
        return status;
    }
}
