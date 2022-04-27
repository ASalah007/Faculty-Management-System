package com.example.fms.model;

import java.util.ArrayList;

public class Instructor extends User {
    private String title;
    private ArrayList<Course> teaches;
    private ArrayList<Student> advices;

    public String getTitle() {
        return title;
    }

    public ArrayList<Course> getTeaches() {
        return teaches;
    }

    public ArrayList<Student> getAdvices() {
        return advices;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTeaches(ArrayList<Course> teaches) {
        this.teaches = teaches;
    }

    public void setAdvices(ArrayList<Student> advices) {
        this.advices = advices;
    }
}
