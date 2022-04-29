package com.example.fms.dao;

import com.example.fms.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoJdbc implements UserDao{
    @Override
    public User findUserByemail(String email) {

        User user  = new User();
        String sql = "select * from users where email =\""+email+"\";";
        Connection conn = null;

        try{
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setBirthdate(rs.getDate("birthdate"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setAddress(rs.getString("address"));
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            Jdbc.closeConnection(conn);
        }
        return user;
    }

    @Override
    public User findUserByID(String id) {
        User user  = new User();
        String sql = "select * from users where id =\""+id+"\";";
        Connection conn = null;

        try{
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                user.setId(rs.getInt("id"));

                user = new User();
                user.setName(rs.getString("name"));
                user.setBirthdate(rs.getDate("birthdate"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setAddress(rs.getString("address"));
                user.setId(rs.getInt("id"));
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            Jdbc.closeConnection(conn);
        }
        return user;
    }

    public static void main(String[] args){
        // quick test
        UserDao obj = new UserDaoJdbc();
        User user = obj.findUserByemail("crodenburgh0@e-recht24.de");
        System.out.println(user.getName());
    }

}
