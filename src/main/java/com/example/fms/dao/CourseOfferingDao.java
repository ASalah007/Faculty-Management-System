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
}
