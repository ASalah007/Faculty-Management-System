package com.example.fms.model;


import java.util.ArrayList;

public class CourseOffering extends Course {
    public enum semester_type {Spring, Summer, Fall, Winter}

    private int year;
    private semester_type semester;
    private int course_offering_id;
    private ArrayList<Course> students;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public semester_type getSemester() {
        return semester;
    }

    public void setSemester(semester_type semester) {
        this.semester = semester;
    }

    public int getCourse_offering_id() {
        return course_offering_id;
    }

    public void setCourse_offering_id(int course_offering_id) {
        this.course_offering_id = course_offering_id;
    }

    public ArrayList<Course> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Course> students) {
        this.students = students;
    }
}
