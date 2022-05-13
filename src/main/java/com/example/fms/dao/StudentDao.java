package com.example.fms.dao;

import com.example.fms.model.CourseOffering;
import com.example.fms.model.Student;

import java.util.ArrayList;

public interface StudentDao {

    //keyword could be name, email, facultyId ...
    ArrayList<Student> findStudentByKeyword(String keyword);

    Student findStudentById(String id);

    ArrayList<Student> findStudentsByCourseOffering(CourseOffering offering);

    void enrollStudent(Student student, CourseOffering offering);

    void unenrollStudent(Student student, CourseOffering offering);

    void approveEnrollment(Student student, CourseOffering offering);

    void setStudentGrade(Student student, CourseOffering offering, int grade);

    // birhtdate is in format "yyyy-mm-dd"
    boolean insertNewStudent(String email, String password, String name, String address, String birthdate);

    int generateFacultyId();

    Student findStudentByFacultyId(String facultyId);

    void refreshStudentGPA(Student student);
}


