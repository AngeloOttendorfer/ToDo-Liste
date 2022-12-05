package com.example.todoliste.model;

public class Task {
    private String task;
    private String date;

    public Task(String task, String date){
        this.task = task;
        this.date = date;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Aufgabe: " + task + ", Fälligkeitsdatum: " + date + "\n";
    }
}
