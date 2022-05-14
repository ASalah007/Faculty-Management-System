package com.example.fms.dao;

import com.example.fms.model.CourseOffering;
import com.example.fms.model.Student;

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
                Student student = studentDaoJdbc.findStudentById(rs.getString("id"));
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
    public Student findStudentById(String id) {
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
                student.setId(rs.getString("id"));
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
                Student student = studentDaoJdbc.findStudentById(rs.getString("id"));
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
        String studentId = student.getId();
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
        String studentId = student.getId();
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
        String studentId = student.getId();
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
        String studentId = student.getId();
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

    @Override
    public boolean insertNewStudent(String email, String password, String name, String address, String birthdate){
        UserDao userDaoJdbc = new UserDaoJdbc();
        int id = userDaoJdbc.insertNewUser(email, password, name, address, birthdate);
        if(id == -1)return false;
        int facultyId = generateFacultyId();
        String sql = "insert into students (id,facultyId) values (\""+id+"\",\""+facultyId+"\");";
        Connection conn = null;
        try{
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
        }
        catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
        finally{
            Jdbc.closeConnection(conn);
        }
        return true;
    }

    @Override
    public int generateFacultyId()
    {
        int facultyId = (int)(Math.random() * 9000) +  2201000;
        String sql = "select * from students where facultyId = \""+facultyId+"\";";
        Connection conn = null;
        try{
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if(rs.next())
            {
                return generateFacultyId();
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            Jdbc.closeConnection(conn);
        }
        return facultyId;
    }

    @Override
    public Student findStudentByFacultyId(String facultyId) {
        Student student  = null;
        String sql = "select * from " +
                "     students s " +
                "     join users u " +
                "     on s.id = u.id" +
                "     where s.facultyId =\""+facultyId+"\";";
        Connection conn = null;

        try{
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                student = new Student();
                student.setId(rs.getString("id"));
                refreshStudentGPA(student);
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
    public void refreshStudentGPA(Student student) {
        String sql = "update students " +
                "set gpa =" +
                "(select sum(credits*credit_hours)/sum(credit_hours)" +
                "    from takes" +
                "    natural join grades_credits" +
                "    natural join course_offerings" +
                "    natural join courses" +
                "    where id = \""+student.getId()+"\")" +
                "where id = \""+student.getId()+"\";";
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


    public static void main(String[] args){
        // quick test
        StudentDao obj = new StudentDaoJdbc();
        boolean result = obj.insertNewStudent("test@gmail.com","test","test","test","1995-01-01");
        System.out.println(result);
    }
}
