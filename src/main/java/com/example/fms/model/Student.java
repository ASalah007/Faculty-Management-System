package com.example.fms.model;

import java.util.ArrayList;

public class Student extends User {
    private double GPA;
    private String faculty_ID;
    private ArrayList<Course> takes;
    private Instructor advisor;

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public String getFaculty_ID() {
        return faculty_ID;
    }

    public void setFaculty_ID(String faculty_ID) {
        this.faculty_ID = faculty_ID;
    }

    public ArrayList<Course> getTakes() {
        return takes;
    }

    public void setTakes(ArrayList<Course> takes) {
        this.takes = takes;
    }

    public Instructor getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Instructor advisor) {
        this.advisor = advisor;
    }
}
