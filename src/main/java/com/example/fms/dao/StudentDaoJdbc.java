package com.example.fms.dao;

import com.example.fms.model.CourseOffering;
import com.example.fms.model.Student;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StudentDaoJdbc implements  StudentDao {

    @Override
    public ArrayList<Student> findStudentByKeyword(String keyword) {
        ArrayList<Student> students = new ArrayList<>();
        String sql = "select s.id" +
                "     from students s " +
                "     join users u" +
                "     on s.id = u.id" +
                "     where s.id like \"%"+keyword+"%\"" +
                "     or s.gpa like \"%"+keyword+"%\"" +
                "     or s.facultyId like \"%"+keyword+"%\"" +
                "     or u.name like \"%"+keyword+"%\"" +
                "     or u.email like \"%"+keyword+"%\"" +
                "     or u.password like \"%"+keyword+"%\"" +
                "     or u.address like \"%"+keyword+"%\"" +
                "     or u.birthdate like \"%"+keyword+"%\";";

        Connection conn = null;

        try{
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            StudentDaoJdbc studentDaoJdbc = new StudentDaoJdbc();
            while(rs.next()){
                Student student = studentDaoJdbc.findStudentById(rs.getInt("id"));
                students.add(student);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            Jdbc.closeConnection(conn);
        }
        return students;
    }

    @Override
    public Student findStudentById(int id) {
        Student student  = new Student();
        String sql = "select * from " +
                "     students s " +
                "     join users u " +
                "     on s.id = u.id" +
                "     where s.id =\""+id+"\";";
        Connection conn = null;

        try{
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                student.setId(rs.getInt("id"));
                student.setGPA(rs.getDouble("gpa"));
                student.setFaculty_ID(rs.getString("facultyId"));
                student.setName(rs.getString("name"));
                student.setBirthdate(rs.getDate("birthdate"));
                student.setEmail(rs.getString("email"));
                student.setPassword(rs.getString("password"));
                student.setAddress(rs.getString("address"));
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            Jdbc.closeConnection(conn);
        }
        return student;
    }

    @Override
    public ArrayList<Student> findStudentsByCourseOffering(CourseOffering offering) {
        int courseOfferingId = offering.getCourse_offering_id();
        ArrayList<Student> students = new ArrayList<>();
        String sql = "select s.id" +
                "     from students s " +
                "     join takes t" +
                "     on s.id = t.id" +
                "     join course_offerings co" +
                "     on t.course_offering_id = co.course_offering_id" +
                "     where co.course_offering_id = \""+courseOfferingId+"\";";
        Connection conn = null;

        try{
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            StudentDaoJdbc studentDaoJdbc = new StudentDaoJdbc();
            while(rs.next()){
                Student student = studentDaoJdbc.findStudentById(rs.getInt("id"));
                students.add(student);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            Jdbc.closeConnection(conn);
        }
        return students;
    }

    @Override
    public void enrollStudent(Student student, CourseOffering offering) {
        int courseOfferingId = offering.getCourse_offering_id();
        int studentId = student.getId();
        String sql = "insert into takes(id,course_offering_id)" +
                "     values(\""+studentId+"\",\""+courseOfferingId+"\");";
        Connection conn = null;

        try{
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            Jdbc.closeConnection(conn);
        }
    }

    @Override
    public void unenrollStudent(Student student, CourseOffering offering) {
        int courseOfferingId = offering.getCourse_offering_id();
        int studentId = student.getId();
        String sql = "delete from takes" +
                "     where id = \""+studentId+"\"" +
                "     and course_offering_id = \""+courseOfferingId+"\";";
        Connection conn = null;

        try{
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            Jdbc.closeConnection(conn);
        }
    }

    @Override
    public void approveEnrollment(Student student, CourseOffering offering) {
        int courseOfferingId = offering.getCourse_offering_id();
        int studentId = student.getId();
        String sql = "update takes" +
                "     set approved = true" +
                "     where id = \""+studentId+"\"" +
                "     and course_offering_id = \""+courseOfferingId+"\";";
        Connection conn = null;

        try{
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            Jdbc.closeConnection(conn);
        }
    }

    @Override
    public void setStudentGrade(Student student, CourseOffering offering, int grade) {
        //it is assumed that the student has already been enrolled in the course
        int courseOfferingId = offering.getCourse_offering_id();
        int studentId = student.getId();
        String sql = "update takes" +
                "     set grade = \""+grade+"\"" +
                "     where id = \""+studentId+"\"" +
                "     and course_offering_id = \""+courseOfferingId+"\";";
        Connection conn = null;

        try{
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            Jdbc.closeConnection(conn);
        }
    }
}
