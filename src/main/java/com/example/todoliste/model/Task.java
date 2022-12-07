package com.example.todoliste.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Task {
    private StringProperty task;
    private StringProperty date;

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

    @Override
    public String toString() {
        return "Aufgabe: " + task + ", Fälligkeitsdatum: " + date + "\n";
    }
}
