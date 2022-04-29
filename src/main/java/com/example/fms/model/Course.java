package com.example.fms.model;

import java.util.ArrayList;

public class Course {
    private int course_ID;
    private String course_code;
    private String name;
    private int credit_hours;
    private String course_description;
    private ArrayList<Course> prerequisites;

    public int getCourse_ID() {
        return course_ID;
    }

    public String getCourse_code() {
        return course_code;
    }

    public String getName() {
        return name;
    }

    public int getCredit_hours() {
        return credit_hours;
    }

    public ArrayList<Course> getPrerequisites() {
        return prerequisites;
    }

    public void setCourse_ID(int course_ID) {
        this.course_ID = course_ID;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCredit_hours(int credit_hours) {
        this.credit_hours = credit_hours;
    }

    public void setPrerequisites(ArrayList<Course> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String getCourse_description() {
        return course_description;
    }

    public void setCourse_description(String course_description) {
        this.course_description = course_description;
    }
}
