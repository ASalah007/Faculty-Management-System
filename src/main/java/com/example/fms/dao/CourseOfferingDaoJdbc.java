package com.example.fms.dao;


import com.example.fms.model.CourseOffering;
import com.example.fms.model.Student;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CourseOfferingDaoJdbc implements CourseOfferingDao {
    @Override
    public ArrayList<CourseOffering> findAllCourseOffering() {
        ArrayList<CourseOffering> courseOfferings = new ArrayList<>();


        String sql= "select * from course_offerings join courses using (course_id);";

        Connection conn = null;

        try {
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                CourseOffering tempCourse= new CourseOffering();
                tempCourse.setYear(rs.getInt("year"));
                tempCourse.setCourse_offering_id(rs.getInt("course_offering_id"));
                tempCourse.setSemester(CourseOffering.semester_type.valueOf(rs.getString("semester")));
                tempCourse.setCourse_ID(rs.getInt("course_id"));
                tempCourse.setCourse_code(rs.getString("course_code"));
                tempCourse.setName(rs.getString("name"));
                tempCourse.setCredit_hours(rs.getInt("credit_hours"));
                tempCourse.setCourse_description(rs.getString("course_description"));
                courseOfferings.add(tempCourse);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            Jdbc.closeConnection(conn);
        }
        return courseOfferings;
    }

    @Override
    public ArrayList<CourseOffering> findStudentOfferings(Student student){
        String student_ID=student.getId();
        ArrayList<CourseOffering> courseOfferings = new ArrayList<>();
        String sql= "select * from course_offerings " +
                "join takes using(course_offering_id) " +
                "join courses using(course_id) " +
                "where id = \""+student_ID+"\";";
        Connection conn = null;

        try {
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                CourseOffering tempCourse= new CourseOffering();
                tempCourse.setYear(rs.getInt("year"));
                tempCourse.setCourse_offering_id(rs.getInt("course_offering_id"));
                tempCourse.setSemester(CourseOffering.semester_type.valueOf(rs.getString("semester")));
                tempCourse.setCourse_ID(rs.getInt("course_id"));
                tempCourse.setCourse_code(rs.getString("course_code"));
                tempCourse.setName(rs.getString("name"));
                tempCourse.setCredit_hours(rs.getInt("credit_hours"));
                tempCourse.setCourse_description(rs.getString("course_description"));
                courseOfferings.add(tempCourse);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            Jdbc.closeConnection(conn);
        }
        return courseOfferings;

    }

    @Override
    public ArrayList<CourseOffering> findApprovedStudentOfferings(Student student) {
        String student_ID=student.getId();
        ArrayList<CourseOffering> courseOfferings = new ArrayList<>();
        String sql= "select * from course_offerings " +
                "join takes using(course_offering_id) " +
                "join courses using(course_id) " +
                "where id = \""+student_ID+"\" and approved = 1;";
        Connection conn = null;

        try {
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                CourseOffering tempCourse= new CourseOffering();
                tempCourse.setYear(rs.getInt("year"));
                tempCourse.setCourse_offering_id(rs.getInt("course_offering_id"));
                tempCourse.setSemester(CourseOffering.semester_type.valueOf(rs.getString("semester")));
                tempCourse.setCourse_ID(rs.getInt("course_id"));
                tempCourse.setCourse_code(rs.getString("course_code"));
                tempCourse.setName(rs.getString("name"));
                tempCourse.setCredit_hours(rs.getInt("credit_hours"));
                tempCourse.setCourse_description(rs.getString("course_description"));
                courseOfferings.add(tempCourse);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            Jdbc.closeConnection(conn);
        }
        return courseOfferings;
    }

    @Override
    public ArrayList<CourseOffering> findPendingStudentOfferings(Student student) {
        String student_ID=student.getId();
        ArrayList<CourseOffering> courseOfferings = new ArrayList<>();
        String sql= "select * from course_offerings " +
                "join takes using(course_offering_id) " +
                "join courses using(course_id) " +
                "where id = \""+student_ID+"\" and approved is null;";
        Connection conn = null;

        try {
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                CourseOffering tempCourse= new CourseOffering();
                tempCourse.setYear(rs.getInt("year"));
                tempCourse.setCourse_offering_id(rs.getInt("course_offering_id"));
                tempCourse.setSemester(CourseOffering.semester_type.valueOf(rs.getString("semester")));
                tempCourse.setCourse_ID(rs.getInt("course_id"));
                tempCourse.setCourse_code(rs.getString("course_code"));
                tempCourse.setName(rs.getString("name"));
                tempCourse.setCredit_hours(rs.getInt("credit_hours"));
                tempCourse.setCourse_description(rs.getString("course_description"));
                courseOfferings.add(tempCourse);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            Jdbc.closeConnection(conn);
        }
        return courseOfferings;
    }
    @Override
    public CourseOffering findCourseOffering(String courseCode, String year, String semester) {
        String sql= "select * from course_offerings " +
                "join courses using (course_id) where course_code = \""+courseCode+"\""
                + "and year = \""+ year + "\" and semester = \"" + semester +"\";";

        Connection conn = null;

        try {
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                CourseOffering tempCourse= new CourseOffering();
                tempCourse.setYear(rs.getInt("year"));
                tempCourse.setCourse_offering_id(rs.getInt("course_offering_id"));
                tempCourse.setSemester(CourseOffering.semester_type.valueOf(rs.getString("semester")));
                tempCourse.setCourse_ID(rs.getInt("course_id"));
                tempCourse.setCourse_code(rs.getString("course_code"));
                tempCourse.setName(rs.getString("name"));
                tempCourse.setCredit_hours(rs.getInt("credit_hours"));
                tempCourse.setCourse_description(rs.getString("course_description"));
                return tempCourse;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            Jdbc.closeConnection(conn);
        }
        return null;
    }
}
