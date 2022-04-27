package com.example.fms.dao;

import com.example.fms.model.CourseOffering;
import com.example.fms.model.Instructor;

import java.util.ArrayList;

public interface InstructorDao {
    Instructor findInstructorById(String id);

    ArrayList<Instructor> findInstructorsByOffering(CourseOffering offering);
}
