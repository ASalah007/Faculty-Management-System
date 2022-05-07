package com.example.fms.dao;

import com.example.fms.model.User;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoJdbc implements UserDao{
    @Override
    public User findUserByemail(String email) {

        User user  = null;
        String sql = "select * from users where email =\""+email+"\";";
        Connection conn = null;

        try{
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                user = new User();
                user.setId(rs.getString("id"));
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
                user.setId(rs.getString("id"));

                user = new User();
                user.setName(rs.getString("name"));
                user.setBirthdate(rs.getDate("birthdate"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setAddress(rs.getString("address"));
                user.setId(rs.getString("id"));
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
    public int insertNewUser(String email, String password, String name, String address, String birthdate) {
        String sql = "insert into users(name,email,password,address,birthdate)" +
                "     values(\""+name+"\" , \""+email+"\" , md5(\""+password+"\") , \""+address+"\" , \""+birthdate+"\");";
        Connection conn = null;
        try
        {
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
            sql = "select LAST_INSERT_ID();";
            statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getInt("LAST_INSERT_ID()");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return -1;
        }
        finally
        {
            Jdbc.closeConnection(conn);
        }
    }

    @Override
    public String hashPassword(String password){
        String md5 = "";
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for(byte b : digest){
                sb.append(String.format("%02x", b & 0xff));
            }
            md5 = sb.toString();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return md5;
    }

    public static void main(String[] args){
        // quick test
        UserDao obj = new UserDaoJdbc();
        int user = obj.insertNewUser("test@gmail.com","123456","test","test","1999-01-01");
        System.out.println(user);
    }

}
