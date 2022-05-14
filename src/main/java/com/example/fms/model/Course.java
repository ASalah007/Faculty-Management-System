package com.example.fms.model;

import java.util.ArrayList;

public class Course {
    private int course_ID;
    private String course_code;
    private String name;
    private int credit_hours;
    private String course_description;
    private ArrayList<Course> prerequisites;

    public int getCourseID() {
        return course_ID;
    }

    public String getCourseCode() {
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

    public void setCourseID(int course_ID) {
        this.course_ID = course_ID;
    }

    public void setCourseCode(String course_code) {
        this.course_code = course_code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreditHours(int credit_hours) {
        this.credit_hours = credit_hours;
    }

    public void setPrerequisites(ArrayList<Course> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String getCourseDescription() {
        return course_description;
    }

    public void setCourseDescription(String course_description) {
        this.course_description = course_description;
    }
}
