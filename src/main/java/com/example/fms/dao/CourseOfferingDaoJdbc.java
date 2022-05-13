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
                tempCourse.setCourseID(rs.getInt("course_id"));
                tempCourse.setCourseCode(rs.getString("course_code"));
                tempCourse.setName(rs.getString("name"));
                tempCourse.setCreditHours(rs.getInt("credit_hours"));
                tempCourse.setCourseDescription(rs.getString("course_description"));
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
                tempCourse.setCourseID(rs.getInt("course_id"));
                tempCourse.setCourseCode(rs.getString("course_code"));
                tempCourse.setName(rs.getString("name"));
                tempCourse.setCreditHours(rs.getInt("credit_hours"));
                tempCourse.setCourseDescription(rs.getString("course_description"));
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
    public ArrayList<CourseOffering> findAvailableStudentOfferings(Student student){
        String student_ID=student.getId();
        ArrayList<CourseOffering> courseOfferings = new ArrayList<>();
        String sql= "select * from course_offerings " +
                "join takes using(course_offering_id) " +
                "join courses using(course_id) " +
                "where course_offering_id not in" +
                "   (select course_offering_id from takes where id = \""+student_ID+"\")" +
                "group by course_offering_id;";
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
                tempCourse.setCourseID(rs.getInt("course_id"));
                tempCourse.setCourseCode(rs.getString("course_code"));
                tempCourse.setName(rs.getString("name"));
                tempCourse.setCreditHours(rs.getInt("credit_hours"));
                tempCourse.setCourseDescription(rs.getString("course_description"));
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
                tempCourse.setCourseID(rs.getInt("course_id"));
                tempCourse.setCourseCode(rs.getString("course_code"));
                tempCourse.setName(rs.getString("name"));
                tempCourse.setCreditHours(rs.getInt("credit_hours"));
                tempCourse.setCourseDescription(rs.getString("course_description"));
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
                tempCourse.setCourseID(rs.getInt("course_id"));
                tempCourse.setCourseCode(rs.getString("course_code"));
                tempCourse.setName(rs.getString("name"));
                tempCourse.setCreditHours(rs.getInt("credit_hours"));
                tempCourse.setCourseDescription(rs.getString("course_description"));
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
                tempCourse.setCourseID(rs.getInt("course_id"));
                tempCourse.setCourseCode(rs.getString("course_code"));
                tempCourse.setName(rs.getString("name"));
                tempCourse.setCreditHours(rs.getInt("credit_hours"));
                tempCourse.setCourseDescription(rs.getString("course_description"));
                return tempCourse;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            Jdbc.closeConnection(conn);
        }
        return null;
    }

    @Override
    public String findStudentGrade(Student student, CourseOffering courseOffering) {
        String sql= "select grade from takes " +
                "join course_offerings using(course_offering_id) " +
                "where id = \""+student.getId()+"\" and course_offering_id = \""+courseOffering.getCourse_offering_id()+"\";";
        Connection conn = null;
        String grade = "No grade yet";
        try {
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String temp = rs.getString("grade");
                if (temp != null) {
                    grade = temp;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            Jdbc.closeConnection(conn);
        }
        return grade;
    }

    public static void main(String[] args) {
        CourseOfferingDaoJdbc dao = new CourseOfferingDaoJdbc();
        StudentDaoJdbc studentDao = new StudentDaoJdbc();
        Student student = studentDao.findStudentById("33");
        ArrayList<CourseOffering> courseOfferings = dao.findAvailableStudentOfferings(student);
        for (CourseOffering courseOffering : courseOfferings) {
            System.out.println(courseOffering.getSemester() + " " + courseOffering.getYear() + " " + courseOffering.getCourseCode());
        }
    }

}
