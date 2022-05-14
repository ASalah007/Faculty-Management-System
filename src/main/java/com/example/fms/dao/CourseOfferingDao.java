package com.example.fms.dao;

import com.example.fms.model.Course;
import com.example.fms.model.CourseOffering;
import com.example.fms.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public interface CourseOfferingDao {
    ArrayList<CourseOffering> findAllCourseOffering();
    ArrayList<CourseOffering> findStudentOfferings(Student student);
    ArrayList<CourseOffering> findAvailableStudentOfferings(Student student);
    ArrayList<CourseOffering> findApprovedStudentOfferings(Student student);
    ArrayList<CourseOffering> findPendingStudentOfferings(Student student); 
    CourseOffering findCourseOffering(String courseCode, String year, String semester);
    String findStudentGrade(Student student, CourseOffering courseOffering);
}
