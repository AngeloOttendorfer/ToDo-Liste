package com.example.todoliste.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Task {
    private StringProperty task;
    private StringProperty day;
    private StringProperty time;
    private StringProperty date;
    private StringProperty info;

    private final StringProperty status = new SimpleStringProperty();

    public Task(String task, String day, String time, String date, String info){
        this.task = new SimpleStringProperty(task);
        this.day = new SimpleStringProperty(day);
        this.time = new SimpleStringProperty(time);
        this.date = new SimpleStringProperty(date);
        this.info = new SimpleStringProperty(info);
    }

    public String getTask() {
        return task.get();
    }

    public void setTask(String task) {
        this.task.set(task);
    }

    public String getDay() {
        return day.get();
    }

    public void setDay(String day) {
        this.day.set(day);
    }

    public String getTime() {
        return time.get();
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getInfo() {
        return info.get();
    }

    public void setInfo(String info) {
        this.info.set(info);
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

    public StringProperty dayProperty(){
        if(day == null){
            day = new SimpleStringProperty("-");
        }
        return day;
    }

    public StringProperty timeProperty(){
        if(time == null){
            time = new SimpleStringProperty("-");
        }
        return time;
    }

    public StringProperty dateProperty(){
        if(date == null){
            date = new SimpleStringProperty("-");
        }
        return date;
    }

    public StringProperty infoProperty(){
        if(info == null){
            info = new SimpleStringProperty("-");
        }
        return info;
    }

    public StringProperty statusProperty(){
        return status;
    }
}