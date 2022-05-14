package com.example.fms.dao;

import com.example.fms.model.Course;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CourseDaoJdbc implements CourseDao {
    @Override
    public Course findCourseByCode(String courseCode) {
        Course course = new Course();
        String sql = "SELECT * FROM courses WHERE course_code= \"" + courseCode + "\";";
        Connection conn = null;

        try {
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                course.setName(rs.getString("name"));
                course.setCourseCode(rs.getString("course_code"));
                course.setCreditHours(rs.getInt("credit_hours"));
                course.setCourseID(rs.getInt("course_id"));
                course.setCourseDescription(rs.getString("course_description"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            Jdbc.closeConnection(conn);
        }
        return course;
    }

    @Override
    public Course findCourseByID(int course_ID) {
        Course course = new Course();
        String sql = "SELECT * FROM courses WHERE course_id= \"" + course_ID + "\";";
        Connection conn = null;

        try {
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                course.setName(rs.getString("name"));
                course.setCourseCode(rs.getString("course_code"));
                course.setCreditHours(rs.getInt("credit_hours"));
                course.setCourseID(rs.getInt("course_id"));
                course.setCourseDescription(rs.getString("course_description"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            Jdbc.closeConnection(conn);
        }
        return course;
    }

    @Override
    public ArrayList<Course> findCoursePrerequisits(Course course) {
        ArrayList<Course> courses = new ArrayList<>();
        int CurrentId = course.getCourseID();
        String sql = "SELECT * FROM prerequisites " +
                "WHERE course_id=\"" + CurrentId + "\";";
        Connection conn = null;

        try {
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            CourseDaoJdbc courseDaoJdbc = new CourseDaoJdbc();
            while (rs.next()) {
                Course tempCourse = courseDaoJdbc.findCourseByID(rs.getInt("course_id"));
                courses.add(tempCourse);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            Jdbc.closeConnection(conn);
        }
        return courses;
    }


    @Override
    public void updateDescription(Course course, String newDescription) {
        int courseID = course.getCourseID();
        String sql = "UPDATE courses " +
                "SET course_description=\"" + newDescription + "\"" +
                "Where course_id=\"" + courseID + "\";";
        Connection conn = null;

        try {
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            Jdbc.closeConnection(conn);
        }
    }

    @Override
    public ArrayList<Course> getAllCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        Connection conn = null;

        try {
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            CourseDaoJdbc courseDaoJdbc = new CourseDaoJdbc();
            while (rs.next()) {
                Course tempCourse = new Course();
                tempCourse.setName(rs.getString("name"));
                tempCourse.setCourseCode(rs.getString("course_code"));
                tempCourse.setCreditHours(rs.getInt("credit_hours"));
                tempCourse.setCourseID(rs.getInt("course_id"));
                tempCourse.setCourseDescription(rs.getString("course_description"));
                courses.add(tempCourse);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            Jdbc.closeConnection(conn);
        }
        return courses;
    }

    public static void main(String[] args){
        // quick test
        CourseDaoJdbc test = new CourseDaoJdbc();
        Course c = test.findCourseByCode("CSE331");
        System.out.println(c.getName());

    }
}
