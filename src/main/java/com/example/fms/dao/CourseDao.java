package com.example.fms.dao;

import com.example.fms.model.Course;

import java.util.ArrayList;

public interface CourseDao {
    Course findCourseByCode(String courseCode);

    Course findCourseByID(int course_ID);

    ArrayList<Course> findCoursePrerequisits(Course course);

    void updateDescription(Course course, String newDescription);

    ArrayList<Course> getAllCourses();
}
