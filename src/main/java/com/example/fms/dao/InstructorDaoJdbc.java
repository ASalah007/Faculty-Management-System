package com.example.fms.dao;

import com.example.fms.model.CourseOffering;
import com.example.fms.model.Instructor;
import com.example.fms.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class InstructorDaoJdbc implements InstructorDao{
    @Override
    public Instructor findInstructorById(String id) {
        String sql = "select * from instructors where id = \""+id+"\";";
        Connection conn = null;

        try{
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                Instructor instructor=new Instructor();
                UserDaoJdbc dummy = new UserDaoJdbc();
                User tempUser = dummy.findUserByID(id);
                instructor.setId(tempUser.getId());
                instructor.setName(tempUser.getName());
                instructor.setAddress(tempUser.getAddress());
                instructor.setEmail(tempUser.getEmail());
                instructor.setPassword(tempUser.getPassword());
                instructor.setBirthdate(tempUser.getBirthdate());
                instructor.setTitle(rs.getString("title"));
                return instructor;
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            Jdbc.closeConnection(conn);
        }
        return null;
    }

    @Override
    public ArrayList<Instructor> findInstructorsByOffering(CourseOffering offering) {
        ArrayList<Instructor> instructors = new ArrayList<>();
        int offering_id=offering.getCourseID();
        String sql="select * from course_offerings " +
                "join teaches using (course_offering_id) " +
                "join users using(id)" +
                "join instructors using(id)" +
                "where course_offering_id = \""+offering_id+"\";";
        Connection conn = null;

        try{
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Instructor instructor= new Instructor();
                instructor.setId(rs.getString("id"));
                instructor.setName(rs.getString("name"));
                instructor.setAddress(rs.getString("address"));
                instructor.setEmail(rs.getString("email"));
                instructor.setPassword(rs.getString("password"));
                instructor.setBirthdate(rs.getDate("birthdate"));
                instructor.setTitle(rs.getString("title"));
                instructors.add(instructor);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            Jdbc.closeConnection(conn);
        }
        return instructors;

    }
}
